/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monkstone.noise;

/**
 *
 * @author Martin Prout
 */
public class NoiseGenerator implements Noise {

    private Noise implementation;
    private NoiseMode mode;
    private Long seed;

    public NoiseGenerator() {
      seed = System.currentTimeMillis();
        this.implementation = new OpenSimplex2F(seed);
        this.mode = NoiseMode.DEFAULT;
    }

    @Override
    public void noiseMode(NoiseMode mode) {
        if (this.mode != mode && this.mode != NoiseMode.DEFAULT) {
            this.implementation = new OpenSimplex2F(seed);
            this.mode = NoiseMode.DEFAULT;
        }
        if (this.mode != mode && this.mode != NoiseMode.OPEN_SMOOTH) {
            this.implementation = new OpenSimplex2S(seed);
            this.mode = NoiseMode.OPEN_SMOOTH;
        }
        if (this.mode != mode && this.mode != NoiseMode.FAST_TERRAIN) {
            this.implementation = new FastTerrain(seed);
            this.mode = NoiseMode.FAST_TERRAIN;
        }     
        
        if (this.mode != mode && this.mode != NoiseMode.SMOOTH_TERRAIN) {
            this.implementation = new SmoothTerrain(seed);
            this.mode = NoiseMode.SMOOTH_TERRAIN;
        }
    }

    public NoiseMode noiseMode(){
        return this.mode;
    }

    @Override
    public float noise(float x, float y, float z) {
        return implementation.noise(x, y, z);
    }

    @Override
    public float noise(float x, float y, float z, float w) {
        return implementation.noise(x, y, z, w);
    }

    @Override
    public void noiseSeed(long seed) {
        this.seed = seed;
    }

}
