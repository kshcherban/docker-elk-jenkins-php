pipelineJob("Delete ES indexes") {

    description("Deletes logstash-* indexes older than 30 days daily")

    triggers {
        cron('H H * * *')
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

        stage('Delete indexes') {
            steps {
                sh("""
/usr/bin/curator_cli --host elasticsearch --port 9200 delete_indices \\
    --ignore_empty_list \\
    --filter_list '[{"filtertype":"age","source":"name","direction":"older","unit":"days","unit_count":30,"timestring": "%Y.%m.%d"},{"filtertype":"pattern","kind":"prefix","value":"logstash-*"}]'
""")
            }
        }
    }
}
''')
        }
    }
}
