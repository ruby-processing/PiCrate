/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2005-12 Ben Fry and Casey Reas
  Copyright (c) 2012-18 The Processing Foundation

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License version 2.1 as published by the Free Software Foundation.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
 */
package processing.pdf;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.ByteBuffer;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.lowagie.text.*;
//import com.lowagie.text.pdf.*;
//import com.lowagie.text.pdf.ByteBuffer;

import processing.awt.PGraphicsJava2D;
import processing.core.*;

/**
 * Thin wrapper for the iText PDF library that handles writing PDF files. The
 * majority of the work in this library is done by
 * <a href="http://www.lowagie.com/iText/">iText</a>.
 * This is currently using iText 2.1.7.
 * The issue is that versions from the 5.x series were slow to handle lots of
 * fonts with the DefaultFontMapper. 2.x seemed a little slower than 1.x, but
 * 5.x took up to 10 times the time to load, meaning a lag of several seconds
 * when starting sketches on a machine that had a good handful of fonts
 * installed. (Like, say, anyone in our target audience. Or me.)
 */
public class PGraphicsPDF extends PGraphicsJava2D {

    /**
     * File being written, if it's a file.
     */
    protected File file;
    /**
     * OutputStream being written to, if using an OutputStream.
     */
    protected OutputStream output;

    protected Document document;
    protected PdfWriter writer;
    protected PdfContentByte content;

    /**
     * Shared across instances because it's incredibly time-consuming to create.
     */
    static protected DefaultFontMapper mapper;
    static protected String[] fontList;


    /*
  public PGraphicsPDF() {
    // PDF always likes native fonts. Always.
    hint(ENABLE_NATIVE_FONTS);
  }
     */
    @Override
    public void setPath(String path) {
        this.path = path;
        if (path != null) {
            file = new File(path);
            if (!file.isAbsolute()) {
                file = null;
            }
        }
        if (file == null) {
            throw new RuntimeException("PGraphicsPDF requires an absolute path "
                    + "for the location of the output file.");
        }
    }

    /**
     * Set the library to write to an output stream instead of a file.
     * @param output
     */
    public void setOutput(OutputStream output) {
        this.output = output;
    }

//  /**
//   * all the init stuff happens in here, in case someone calls size()
//   * along the way and wants to hork things up.
//   */
//  protected void allocate() {
//    // can't do anything here, because this will be called by the
//    // superclass PGraphics, and the file/path object won't be set yet
//    // (since super() called right at the beginning of the constructor)
//  }
    @Override
    public PSurface createSurface() {
        return surface = new PSurfaceNone(this);
    }

    @Override
    protected void defaultSettings() {  // ignore
        super.defaultSettings();
        textMode = SHAPE;
    }

    @Override
    public void beginDraw() {
//    long t0 = System.currentTimeMillis();

        if (document == null) {
            // https://github.com/processing/processing/issues/5801#issuecomment-466632459
            ByteBuffer.HIGH_PRECISION = true;

            document = new Document(new Rectangle(width, height));
            boolean missingPath = false;
            try {
                if (file != null) {
                    //BufferedOutputStream output = new BufferedOutputStream(stream, 16384);
                    output = new BufferedOutputStream(new FileOutputStream(file), 16384);

                } else if (output == null) {
                    missingPath = true;
                    throw new RuntimeException("PGraphicsPDF requires a path "
                            + "for the location of the output file.");
                }
                try {
                    writer = PdfWriter.getInstance(document, output);
                } catch (DocumentException ex) {
                    Logger.getLogger(PGraphicsPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
                document.open();
                content = writer.getDirectContent();
//        template = content.createTemplate(width, height);

            } catch (RuntimeException re) {
                if (missingPath) {
                    throw re;  // don't re-package our own error
                } else {
                    throw new RuntimeException("Problem saving the PDF file.", re);
                }

            } catch (FileNotFoundException fnfe) {
                throw new RuntimeException("Can't save the PDF file to " + path, fnfe);

            }

            g2 = content.createGraphicsShapes(width, height);
        }

        // super in Java2D now creates an image buffer, don't do that
        //super.beginDraw();
        checkSettings();
        resetMatrix(); // reset model matrix
        vertexCount = 0;

        // Also need to push the matrix since the matrix doesn't reset on each run
        // http://dev.processing.org/bugs/show_bug.cgi?id=1227
        pushMatrix();
    }

    static protected DefaultFontMapper getMapper() {
        if (mapper == null) {
//      long t = System.currentTimeMillis();
            mapper = new DefaultFontMapper();

            if (PApplet.platform == PConstants.LINUX) {
                checkDir("/usr/share/fonts/", mapper);
                checkDir("/usr/local/share/fonts/", mapper);
                checkDir(System.getProperty("user.home") + "/.fonts", mapper);
            }
//      System.out.println("mapping " + (System.currentTimeMillis() - t));
        }
        return mapper;
    }

    static protected void checkDir(String path, DefaultFontMapper mapper) {
        File folder = new File(path);
        if (folder.exists()) {
            mapper.insertDirectory(path);
            traverseDir(folder, mapper);
        }
    }

    /**
     * Recursive walk to get all subdirectories for font fun.Patch submitted by
 Matthias Breuer.(<a href="http://dev.processing.org/bugs/show_bug.cgi?id=1566">Bug
 1566</a>)
     * @param folder
     * @param mapper
     */
    static protected void traverseDir(File folder, DefaultFontMapper mapper) {
        File[] files = folder.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) {
                mapper.insertDirectory(file1.getPath());
                traverseDir(new File(file1.getPath()), mapper);
            }
        }
    }

    // endDraw() needs to be overridden so that the endDraw() from
    // PGraphicsJava2D is not inherited (it calls loadPixels).
    // http://dev.processing.org/bugs/show_bug.cgi?id=1169
    @Override
    public void endDraw() {
        // Also need to pop the matrix since the matrix doesn't reset on each run
        // http://dev.processing.org/bugs/show_bug.cgi?id=1227
        popMatrix();
    }

    /**
     * Gives the same basic functionality of File.exists but can be used to look
     * for removable media without showing a system dialog if the media is not
     * present.Workaround pulled from the
     * <A HREF="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4089199">
     * bug report</A> on bugs.sun.com.This bug was fixed in Java 6, and we can
     * remove the workaround when we start requiring Java 6.
     *
     * @param file
     * @return
     */
    protected static boolean fileExists(File file) {
        try {
            Process process
                    = Runtime.getRuntime().exec(new String[]{
                "cmd.exe", "/c", "dir", file.getAbsolutePath()
            });

            // We need to consume all available output or the process will block.
            boolean haveExitCode = false;
            int exitCode = -1;
            InputStream out = process.getInputStream();
            InputStream err = process.getErrorStream();

            while (!haveExitCode) {
                while (out.read() >= 0) {
                }
                while (err.read() >= 0) {
                }

                try {
                    exitCode = process.exitValue();
                    haveExitCode = true;
                } catch (IllegalThreadStateException e) {
                    // Not yet complete.
                    Thread.sleep(100);
                }
            }
            //int exitCode = process.waitFor();
            return exitCode == 0;

        } catch (IOException e) {
            System.out.println("Unable to check for file: " + file + " : " + e);
            return false;

        } catch (InterruptedException e) {
            System.out.println("Unable to check for file.  Interrupted: "
                    + file + " : " + e);
            return false;
        }
    }

    /**
     * Call to explicitly go to the next page from within a single draw().
     */
    public void nextPage() {
        PStyle savedStyle = getStyle();
        endDraw();
        g2.dispose();

        try {
//    writer.setPageEmpty(false);  // maybe useful later
            document.newPage();  // is this bad if no addl pages are made?
        } catch (Exception e) {
        }
        g2 = createGraphics();
        beginDraw();
        style(savedStyle);
    }

    protected Graphics2D createGraphics() {
        if (textMode == SHAPE) {
          return content.createGraphics(width, height);
        } else if (textMode == MODEL) {
            return content.createGraphics(width, height, getMapper());
        }
        // Should not be reachable...
        throw new RuntimeException("Invalid textMode() selected for PDF.");
    }

    @Override
    public void dispose() {
        if (document != null) {
            g2.dispose();
            document.close();  // can't be done in finalize, not always called
            document = null;
        }
        //new Exception().printStackTrace(System.out);
    }

    /**
     * Don't open a window for this renderer, it won't be used.
     * @return 
     */
    @Override
    public boolean displayable() {
        return false;
    }

    /*
  protected void finalize() throws Throwable {
    System.out.println("calling finalize");
  //document.close();  // do this in dispose instead?
  }
     */
    //////////////////////////////////////////////////////////////
    /*
  public void endRecord() {
    super.endRecord();
    dispose();
  }


  public void endRaw() {
    System.out.println("ending raw");
    super.endRaw();
    System.out.println("disposing");
    dispose();
    System.out.println("done");
  }
     */
    //////////////////////////////////////////////////////////////
    /*
  protected void rectImpl(float x1, float y1, float x2, float y2) {
    //rect.setFrame(x1, y1, x2-x1, y2-y1);
    //draw_shape(rect);
    System.out.println("rect implements");
    g2.fillRect((int)x1, (int)y1, (int) (x2-x1), (int) (y2-y1));
  }
  *

  /*
  public void clear() {
    g2.setColor(Color.red);
    g2.fillRect(0, 0, width, height);
  }
     */
    //////////////////////////////////////////////////////////////
    @Override
    protected void imageImpl(PImage image,
            float x1, float y1, float x2, float y2,
            int u1, int v1, int u2, int v2) {
        pushMatrix();
        translate(x1, y1);
        int imageWidth = image.width;
        int imageHeight = image.height;
        scale((x2 - x1) / imageWidth,
                (y2 - y1) / imageHeight);
        if (u2 - u1 == imageWidth && v2 - v1 == imageHeight) {
            g2.drawImage((Image) image.getNative(), 0, 0, null);
        } else {
            PImage tmp = image.get(u1, v1, u2 - u1, v2 - v1);
            g2.drawImage((Image) tmp.getNative(), 0, 0, null);
        }
        popMatrix();
    }

    //////////////////////////////////////////////////////////////
    @Override
    public void textFont(PFont which) {
        super.textFont(which);
        checkFont();
        // Make sure a native version of the font is available.
//    if (textFont.getFont() == null) {
//      throw new RuntimeException("Use createFont() instead of loadFont() " +
//                                 "when drawing text using the PDF library.");
//    }
        // Make sure that this is a font that the PDF library can deal with.
//    if ((textMode != SHAPE) && !checkFont(which.getName())) {
//      System.err.println("Use PGraphicsPDF.listFonts() to get a list of available fonts.");
//      throw new RuntimeException("The font “" + which.getName() + "” cannot be used with PDF Export.");
//    }
    }

    /**
     * Change the textMode() to either SHAPE or MODEL.
     * This resets all renderer settings, and therefore must be called
     * <EM>before</EM> any other commands that set the fill() or the textFont()
     * or anything. Unlike other renderers, use textMode() directly after the
     * size() command.
     */
    @Override
    public void textMode(int mode) {
        if (textMode != mode) {
            switch (mode) {
                case SHAPE:
                    textMode = SHAPE;
                    g2.dispose();
                    //        g2 = content.createGraphicsShapes(width, height);
                    g2 = createGraphics();
                    break;
                case MODEL:
                    textMode = MODEL;
                    g2.dispose();
                    //        g2 = content.createGraphics(width, height, mapper);
                    g2 = createGraphics();
//        g2 = template.createGraphics(width, height, mapper);
                    break;
                case SCREEN:
                    throw new RuntimeException("textMode(SCREEN) not supported with PDF");
                default:
                    throw new RuntimeException("That textMode() does not exist");
            }
        }
    }

    @Override
    protected void textLineImpl(char buffer[], int start, int stop,
            float x, float y) {
        checkFont();
        super.textLineImpl(buffer, start, stop, x, y);
    }

    //////////////////////////////////////////////////////////////
    @Override
    public void loadPixels() {
        nope("loadPixels");
    }

    @Override
    public void updatePixels() {
        nope("updatePixels");
    }

    @Override
    public void updatePixels(int x, int y, int c, int d) {
        nope("updatePixels");
    }

    //
    @Override
    public int get(int x, int y) {
        nope("get");
        return 0;  // not reached
    }

    @Override
    public PImage get(int x, int y, int c, int d) {
        nope("get");
        return null;  // not reached
    }

    @Override
    public PImage get() {
        nope("get");
        return null;  // not reached
    }

    @Override
    public void set(int x, int y, int argb) {
        nope("set");
    }

    @Override
    public void set(int x, int y, PImage image) {
        nope("set");
    }

    //

    /**
     *
     * @param alpha
     */
    @Override
    public void mask(int alpha[]) {
        nope("mask");
    }

    @Override
    public void mask(PImage alpha) {
        nope("mask");
    }

    /**
     *
     * @param kind
     */
    @Override
    public void filter(int kind) {
        nope("filter");
    }

    @Override
    public void filter(int kind, float param) {
        nope("filter");
    }

    //
    @Override
    protected void blendModeImpl() {
        if (blendMode != REPLACE && blendMode != BLEND) {
            showMissingWarning("blendMode");
        }
    }

    //
    @Override
    public void copy(int sx1, int sy1, int sx2, int sy2,
            int dx1, int dy1, int dx2, int dy2) {
        nope("copy");
    }

    /**
     *
     * @param src
     * @param sx1
     * @param sy1
     * @param sx2
     * @param sy2
     * @param dx1
     * @param dy1
     * @param dx2
     * @param dy2
     */
    @Override
    public void copy(PImage src,
            int sx1, int sy1, int sx2, int sy2,
            int dx1, int dy1, int dx2, int dy2) {
        nope("copy");
    }

    //
    public void blend(int sx, int sy, int dx, int dy, int mode) {
        nope("blend");
    }

    public void blend(PImage src,
            int sx, int sy, int dx, int dy, int mode) {
        nope("blend");
    }

    @Override
    public void blend(int sx1, int sy1, int sx2, int sy2,
            int dx1, int dy1, int dx2, int dy2, int mode) {
        nope("blend");
    }

    @Override
    public void blend(PImage src,
            int sx1, int sy1, int sx2, int sy2,
            int dx1, int dy1, int dx2, int dy2, int mode) {
        nope("blend");
    }

    //
    @Override
    public boolean save(String filename) {
        nope("save");
        return false;
    }

    //////////////////////////////////////////////////////////////
    /**
     * On Linux or any other platform, you'll need to add the directories by
     * hand. (If there are actual standards here that we can use as a starting
     * point, please file a bug to make a note of it)
     * @param directory
     */
    public void addFonts(String directory) {
        mapper.insertDirectory(directory);
    }

    /**
     * Check whether the specified font can be used with the PDF library.
     *
     */
    protected void checkFont() {
        Font awtFont = (Font) textFont.getNative();
        if (awtFont == null) {  // always need a native font or reference to it
            throw new RuntimeException("Use createFont() instead of loadFont() "
                    + "when drawing text using the PDF library.");
        } else if (textMode != SHAPE) {
            if (textFont.isStream()) {
                throw new RuntimeException("Use textMode(SHAPE) with PDF when loading "
                        + ".ttf and .otf files with createFont().");
            } else if (mapper.getAliases().get(textFont.getName()) == null) {
                //System.out.println("alias for " + name + " = " + mapper.getAliases().get(name));
//        System.err.println("Use PGraphicsPDF.listFonts() to get a list of " +
//                           "fonts that can be used with PDF.");
//        throw new RuntimeException("The font “" + textFont.getName() + "” " +
//                                   "cannot be used with PDF Export.");
                if (textFont.getName().equals("Lucida Sans")) {
                    throw new RuntimeException("Use textMode(SHAPE) with the default "
                            + "font when exporting to PDF.");
                } else {
                    throw new RuntimeException("Use textMode(SHAPE) with "
                            + "“" + textFont.getName() + "” "
                            + "when exporting to PDF.");
                }
            }
        }
    }

    /**
     * List the fonts known to the PDF renderer.This is like PFont.list(),
 however not all those fonts are available by default.
     * @return 
     */
    static public String[] listFonts() {
        if (fontList == null) {
            HashMap<?, ?> map = getMapper().getAliases();
//      Set entries = map.entrySet();
//      fontList = new String[entries.size()];
            fontList = new String[map.size()];
            int count = 0;
            for (Object key : map.keySet()) {
//      for (Object entry : map.entrySet()) {
//        fontList[count++] = (String) ((Map.Entry) entry).getKey();
                fontList[count++] = (String) key;
            }
//      Iterator it = entries.iterator();
//      int count = 0;
//      while (it.hasNext()) {
//        Map.Entry entry = (Map.Entry) it.next();
//        //System.out.println(entry.getKey() + "-->" + entry.getValue());
//        fontList[count++] = (String) entry.getKey();
//      }
            fontList = PApplet.sort(fontList);
        }
        return fontList;
    }

    //////////////////////////////////////////////////////////////
    protected void nope(String function) {
        throw new RuntimeException("No " + function + "() for " + getClass().getSimpleName());
    }
}
