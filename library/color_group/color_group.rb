java_import Java::Monkstone::ColorUtil

# class wraps a java color array (of signed int), supports shuffle!, last and
# ruby_code (string for use in ruby code). As well as ability to initialize
# with an ruby array of "web" color string
class ColorGroup
  attr_reader :colors
  def initialize(p5cols)
    @colors = p5cols
  end

  def self.from_web_array(web)
    ColorGroup.new(ColorUtil.web_array(web))
  end

  def shuffle!
    @colors = ColorUtil.shuffle(colors)
  end

  def ruby_string
    ColorUtil.rubyString(colors)
  end

  def last
    colors[0]
  end
end
