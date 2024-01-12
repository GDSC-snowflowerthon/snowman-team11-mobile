package com.snowflowerthon.snowman.data.dto.response

data class WeatherResponseDto(
    val firstBranch: FirstBranch,
    val location: String,
    val mainBranch: MainBranch,
    val secondBranch: SecondBranch,
    val temperature: Int,
    val thirdBranch: ThirdBranch,
    val weatherId: Int,
    val weatherStatus: String
)

data class FirstBranch(
    val branchTime: String,
    val headWear: String,
    val highestTemperature: Int,
    val lowestTemperature: Int,
    val neckWear: String,
    val outerWear: String,
    val topWear: String
)

data class MainBranch(
    val branchTime: Any,
    val headWear: String,
    val highestTemperature: Any,
    val lowestTemperature: Any,
    val neckWear: String,
    val outerWear: String,
    val topWear: String
)
data class SecondBranch(
    val branchTime: String,
    val headWear: String,
    val highestTemperature: Int,
    val lowestTemperature: Int,
    val neckWear: String,
    val outerWear: String,
    val topWear: String
)
data class ThirdBranch(
    val branchTime: String,
    val headWear: String,
    val highestTemperature: Int,
    val lowestTemperature: Int,
    val neckWear: String,
    val outerWear: String,
    val topWear: String
)