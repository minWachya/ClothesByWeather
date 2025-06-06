package com.example.clothesbyweather.domain.entity

enum class CategoryType {
    TMP, // 1시간 기온
    POP, // 강수 확률
    PTY, // 강수 형태
    REH, // 습도
    SKY, // 하늘 상태

    PCP, // 1시간 강수량
    SNO, // 1시간 신적설
    TMN, // 일 최저 기온
    TMX, // 일 최고 기온
    UUU, // 풍속(동서성분)
    VVV, // 풍속(남북성분)
    WAV, // 파고
    VEC, // 풍향
    WSD // 풍속
}