package minutesock.ecs

interface Component<C> {
    val id: Int? get() = null

    //todo-tyler override equality check this::class == other::class
}
