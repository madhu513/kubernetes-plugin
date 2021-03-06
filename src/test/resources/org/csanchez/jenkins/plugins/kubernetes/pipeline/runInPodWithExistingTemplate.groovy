node ('busybox') {
    sh 'echo outside container'

    sh '''set +x
    echo OUTSIDE_CONTAINER_ENV_VAR = $CONTAINER_ENV_VAR
    echo OUTSIDE_CONTAINER_ENV_VAR_LEGACY = $CONTAINER_ENV_VAR_LEGACY
    echo OUTSIDE_CONTAINER_ENV_VAR_FROM_SECRET = $CONTAINER_ENV_VAR_FROM_SECRET or `echo $CONTAINER_ENV_VAR_FROM_SECRET | tr [a-z] [A-Z]`
    echo OUTSIDE_POD_ENV_VAR = $POD_ENV_VAR
    echo OUTSIDE_POD_ENV_VAR_FROM_SECRET = $POD_ENV_VAR_FROM_SECRET or `echo $POD_ENV_VAR_FROM_SECRET | tr [a-z] [A-Z]`
    echo "OUTSIDE_EMPTY_POD_ENV_VAR_FROM_SECRET = '$EMPTY_POD_ENV_VAR_FROM_SECRET'"
    echo OUTSIDE_GLOBAL = $GLOBAL
    '''

    stage('Run busybox') {
        container('busybox') {
            sh 'echo inside container'
            sh '''set +x
            echo INSIDE_CONTAINER_ENV_VAR = $CONTAINER_ENV_VAR
            echo INSIDE_CONTAINER_ENV_VAR_LEGACY = $CONTAINER_ENV_VAR_LEGACY
            echo INSIDE_CONTAINER_ENV_VAR_FROM_SECRET = $CONTAINER_ENV_VAR_FROM_SECRET or `echo $CONTAINER_ENV_VAR_FROM_SECRET | tr [a-z] [A-Z]`
            echo INSIDE_POD_ENV_VAR = $POD_ENV_VAR
            echo INSIDE_POD_ENV_VAR_FROM_SECRET = $POD_ENV_VAR_FROM_SECRET or `echo $POD_ENV_VAR_FROM_SECRET | tr [a-z] [A-Z]`
            echo "INSIDE_EMPTY_POD_ENV_VAR_FROM_SECRET = '$EMPTY_POD_ENV_VAR_FROM_SECRET'"
            echo INSIDE_GLOBAL = $GLOBAL
            '''
        }
    }
}
