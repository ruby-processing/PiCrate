require 'rbconfig'

# Utility to load native binaries on Java CLASSPATH
class NativeFolder
  attr_reader :os, :bit

  LINUX_FORMAT = 'linux%s'.freeze
  ARM32 = '-armv6hf'.freeze
  # ARM64 = '-aarch64'.freeze

  def initialize
    @os = RbConfig::CONFIG['host_os'].downcase
    @bit = java.lang.System.get_property('os.arch')
  end

  def name
    return format(LINUX_FORMAT, '64') if /linux/.match?(os) && /amd64/.match?(bit)
    format(LINUX_FORMAT, ARM32)
  end

  def extension
    return '*.so' if /linux/.match?(os)
    raise RuntimeError, "Unsupported Archicture"
  end
end
