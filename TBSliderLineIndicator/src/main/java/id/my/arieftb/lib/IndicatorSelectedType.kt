package id.my.arieftb.lib

enum class IndicatorSelectedType(val value: Int) {
    RANGE(0), EACH(1);

    companion object {
        fun fetch(value: Int): IndicatorSelectedType {
            return when (value) {
                RANGE.value -> RANGE
                EACH.value -> EACH
                else -> RANGE
            }
        }
    }
}
