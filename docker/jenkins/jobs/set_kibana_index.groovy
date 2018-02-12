pipelineJob("Set logstash index in kibana") {

    description("Sets default search index in kibana")

    triggers {
        cron('* * * * *')
    }

    definition {
        cps {
            sandbox()
            script('''
pipeline {

    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '30'))
        ansiColor('xterm')
    }

    stages {

        stage('Set index') {
            steps {
                ws('/tmp/jobs') {
                    sh('bash ./create-index-kibana.sh')
                }
            }
        }
    }
}
''')
        }
    }
}
