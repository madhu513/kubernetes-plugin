podTemplate(label: 'mypod', containers: [
        containerTemplate(name: 'busybox', image: 'busybox', ttyEnabled: true, command: '/bin/cat'),
    ]) {

    node ('mypod') {
      stage('Run') {
        container('busybox') {
          sh """
            ## durable-task plugin generates a script.sh file.
            ##
            echo "script file: \$(find ../../.. -iname script.sh))"
            echo "script file contents: \$(find ../../.. -iname script.sh -exec cat {} \\;)"
            test -n "\$(cat \$(find ../../.. -iname script.sh))"
          """
        }
      }
    }
}

podTemplate(label: 'mypod2', containers: [
        containerTemplate(name: 'busybox2', image: 'busybox', ttyEnabled: true, command: '/bin/cat'),
]) {

    node ('mypod2') {
        stage('Run') {
            container('busybox2') {
                sh """
            ## durable-task plugin generates a script.sh file.
            ##
            echo "script file: \$(find ../../.. -iname script.sh))"
            echo "script file contents: \$(find ../../.. -iname script.sh -exec cat {} \\;)"
            test -n "\$(cat \$(find ../../.. -iname script.sh))"
          """
            }
        }
    }
}
