# frozen_string_literal: true

require 'erb'

def geany_config
  %(
[editor]
line_wrapping=false
line_break_column=72
auto_continue_multiline=true

[file_prefs]
final_new_line=true
ensure_convert_new_lines=false
strip_trailing_spaces=true
replace_tabs=true

[indentation]
indent_width=2
indent_type=0
indent_hard_tab_width=8
detect_indent=false
detect_indent_width=false
indent_mode=2

[project]
name=picrate_samples
base_path=<%= home %>
description=Exploring PiCrate
file_patterns=*.rb;*.glsl;*.txt;

[long line marker]
long_line_behaviour=1
long_line_column=72

[files]

[VTE]
last_dir=<%= directory %>

[build-menu]
EX_00_LB=_Execute
EX_00_CM=jruby --dev "%f"
EX_00_WD=
RubyFT_00_LB=_Rubocop
RubyFT_00_CM=rubocop --fail-level F "%f"
RubyFT_00_WD=
filetypes=Ruby;
RubyFT_01_LB=_Rubocop autocorrect
RubyFT_01_CM=rubocop -a  --fail-level F "%f"
RubyFT_01_WD=
NF_00_LB=
NF_00_CM=
NF_00_WD=
NF_01_LB=
NF_01_CM=
NF_01_WD=
NF_02_LB=
NF_02_CM=
NF_02_WD=
RubyFT_02_LB=_Reek
RubyFT_02_CM=reek --failure-exit-code=0 "%f"
RubyFT_02_WD=
  )
end

# Class to merge ERB template and write config to file
class GeanyConfig
  include ERB::Util
  attr_accessor :home, :directory, :template

  def initialize(home, directory, template = geany_config)
    @home = home
    @directory = File.join(home, directory)
    @template = template
  end

  def render
    ERB.new(@template).result(binding)
  end

  def save(file)
    File.open(file, 'w+') do |f|
      f.write(render)
    end
  end
end
