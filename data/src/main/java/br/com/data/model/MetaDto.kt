package br.com.data.model

data class MetaDto(
    val breed_status: String,
    val coat: String,
    val colour: String,
    val common_nicknames: String,
    val foundation_stock: String,
    val heightDto: HeightDto,
    val img_src_setDto: ImgSrcSetDto,
    val life_span: String,
    val litter_size: String,
    val notes: String,
    val other_names: String,
    val weight: String
)