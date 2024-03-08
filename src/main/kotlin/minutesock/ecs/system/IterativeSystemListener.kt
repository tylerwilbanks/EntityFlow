package minutesock.ecs.system

interface IterativeSystemListener {
    fun onPreUpdate(event: SystemEvent.PreUpdate)
    fun onPostUpdate(event: SystemEvent.PostUpdate)
}