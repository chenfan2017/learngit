#!/bin/bash

start() {
	# Check if it is already running
	echo -n $"--- The samconfigjsonview is starting...`date` "
        pid=`ps -ef | grep java | grep samconfigjsonview.jar | awk '{print $2}'`
	if [ ! -z "${pid}" ]; then
	    echo "--- samconfigjsonview has started already"
	    return 1
	fi
	if [ ! -f META-INF/MANIFEST.MF ]; then
            jar -xf lib/samconfigjsonview.jar META-INF/MANIFEST.MF
            sed -i 's/\r//' META-INF/MANIFEST.MF
   fi
	jvm=`cat META-INF/MANIFEST.MF | grep JVM | awk -FJVM: '{print $2}'`
   nohup java -jar $jvm lib/samconfigjsonview.jar >> logs/samconfigjsonview.log & 2>&1 &
   sleep 1
   tail -f logs/samconfigjsonview.log
}

start_ssh() {
	# Check if it is already running
	echo -n $"--- The samconfigjsonview is starting...`date` "
        pid=`ps -ef | grep java | grep samconfigjsonview.jar | awk '{print $2}'`
	if [ ! -z "${pid}" ]; then
	    echo "--- samconfigjsonview has started already"
	    return 1
	fi
	if [ ! -f META-INF/MANIFEST.MF ]; then
            jar -xf lib/samconfigjsonview.jar META-INF/MANIFEST.MF
            sed -i 's/\r//' META-INF/MANIFEST.MF
   fi
	jvm=`cat META-INF/MANIFEST.MF | grep JVM | awk -FJVM: '{print $2}'`
   nohup java -jar $jvm lib/samconfigjsonview.jar >> logs/samconfigjsonview.log & 2>&1 &
   sleep 1
   tail -n 1 logs/samconfigjsonview.log
}

stop() {
	echo -n $"--- The samconfigjsonview is stoping... "
	pid=`ps -ef | grep java | grep samconfigjsonview.jar |awk '{print $2}'`
    if [ ! -z "${pid}" ]; then
		kill -9 $pid
		echo " [ OK ]"
		return 0
	else
		echo " [ FAILED ], samconfigjsonview has stopped already"
		return 1
	fi
}

status() {
	pid=`ps -ef | grep java | grep samconfigjsonview.jar |awk '{print $2}'`
	if [ ! -z "${pid}" ]; then
		echo "--- samconfigjsonview is runnig, pid is ${pid}"
	else
		echo "--- samconfigjsonview is stopped"
	fi
	return 0
}

restart() {
	stop
	start
}

case "$1" in
start)
	start
	;;
start_ssh)
	start_ssh
	;;
stop)
	stop
	;;
restart)
	restart
	;;
status)
	status
	;;
*)
	echo $"Usage: $0 {start|stop|status|restart}"
	exit 1
esac

exit 0

