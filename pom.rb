# frozen_string_literal: true

project 'picrate', 'http://maven.apache.org' do
  model_version '4.0.0'
  id 'ruby-processing:picrate:2.5.0'
  packaging 'jar'

  description 'An integrated processing-core (somewhat hacked), with additional java code for a jruby version of processing.'

  organization 'ruby-processing', 'https://ruby-processing.github.io'

  {
    'monkstone' => 'Martin Prout', 'benfry' => 'Ben Fry',
    'REAS' => 'Casey Reas', 'codeanticode' => 'Andres Colubri'
  }.each do |key, value|
    developer key do
      name value
      roles 'developer'
    end
  end
  license 'GPL 3', 'http://www.gnu.org/licenses/gpl-3.0-standalone.html'
  license 'LGPL 2', 'https://www.gnu.org/licenses/old-licenses/lgpl-2.1.en.html'

  issue_management 'https://github.com/ruby-processing/PiCrate/issues', 'Github'
  # Need to update to jogl 2.4.1 as soon as available, then make a dependency
  properties('jogl.version' => '2.3.2',
             'jruby.version' => '9.3.1.0',
             'batik.version' => '1.14',
             'itextpdf.version' => '5.5.13.2',
             'jruby.api' => 'http://jruby.org/apidocs/',
             'source.directory' => 'src',
             'processing.api' => 'http://processing.github.io/processing-javadocs/core/',
             'picrate.basedir' => '${project.basedir}',
             'project.build.sourceEncoding' => 'UTF-8',
             'polyglot.dump.pom' => 'pom.xml')

  jar 'org.jruby:jruby-base:${jruby.version}'
  jar 'org.jogamp.jogl:jogl-all:${jogl.version}'
  jar 'org.jogamp.gluegen:gluegen-rt-main:${jogl.version}'
  jar 'org.processing:video:3.0.2'
  jar 'org.apache.xmlgraphics:batik-all:${batik.version}'
  jar 'com.itextpdf:itextpdf:${itextpdf.version}'
end

overrides do
  plugin :resources, '3.1.0'
  plugin :dependency, '3.1.2' do
    execute_goals( id: 'default-cli',
      artifactItems:[
        { groupId: 'com.itextpdf',
          artifactId: 'itextpdf',
          version: '${itextpdf.version}',
          type: 'jar',
          outputDirectory: '${picrate.basedir}/library/pdf'
        },
        { groupId: 'org.apache.xmlgraphics',
          artifactId: 'batik-all',
          version: '${batik.version}',
          type: 'jar',
          outputDirectory: '${picrate.basedir}/library/svg'
        }
      ]
    )
  end
  plugin(:compiler, '3.8.1',
         'release' => '11')
  plugin(:javadoc, '2.10.4',
         'detectOfflineLinks' => 'false',
         'links' => ['${processing.api}',
                     '${jruby.api}'])
  plugin(:jar, '3.2.0',
    'archive' => {
      'manifestEntries' => {
        'Automatic-Module-Name' => 'processing.core'
      }
    }
  )
  plugin :jdeps, '3.1.2' do
    execute_goals 'jdkinternals', 'test-jdkinternals'
  end
end

build do
  resource do
    directory '${source.directory}/main/java'
    excludes '**/**/*.java'
  end
  resource do
    directory '${source.directory}/main/resources'
    includes '**/*.png', '**/*.txt', '**/*.glsl'
  end
end
