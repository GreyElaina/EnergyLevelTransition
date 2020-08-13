[English](README.md)  [简体中文](README-zh_cn.md)

# Energy Level Transition 1.16 ![Build](https://github.com/MoegTech/EnergyLevelTransition/workflows/Build/badge.svg) 

This is the development repository of the Minecraft Fabric Mod - Energy Level Transition on Minecraft 1.16

# Table Of Contents

- [Energy Level Transition 1.16](#energy-level-transition-116)
- [Table Of Contents](#table-of-contents)
- [Introduction](#introduction)
- [Dependencies](#dependencies)
- [License](#license)
- [About Development](#about-development)
- [Features](#features)
- [Development Guide](#development-guide)
  - [Setup](#setup)
  - [How to Build](#how-to-build)
  
# Introduction

This is a Tech Mod - which illustrates Realism with hidden Magical Elements - of World Generation, Natural Environment, Player Properties, and more. 

# Dependencies

- Cardinal Components API
- Lib GUI
- Advanced Runtime Resource Pack

They are Jar-in-Jar with the mod so users don't need to download them separately. 

## License

This Mod is available under the MIT License. 

# Development Guide

## Setup

For setup instructions of Fabric Mod please see the [fabric wiki page](https://fabricmc.net/wiki/tutorial:setup) that relates to the IDE that you are using.

## How to Build

1. Clone ELT Base mod: https://github.com/MoegTech/EnergyLevelTransition.git

2. Clone ELT Core: https://github.com/MoegTech/ELTCore.git

3. Now you have two downloaded zip files. Unzip them and rename them to `ELT-1.16` and `EltCore-1.16`. Naming them properly is important!

4. Create a new folder and rename it into `ELT_Project`. This will be the project directory of ELT. 

5. Put `ELT-1.16` and `EltCore-1.16` folders under ELT_Project directory. 

6. Use IntelliJ IDEA or Eclipse's `Import` function to choose `build.gradle` file in ELT-1.16 folder, and `import as project`. (Eclipse users might need to run `gradlew eclipse` after importing it) 

7. Wait for Gradle project to sync. 

8. Run `gradlew genSources` under `ELT-1.16` directory to generate MC sources for reference. 

9. Run 'gradlew build' under 'ELT-1.16' direcotry to build artifacts in both `./ELT_Project/ELT-1.16/build/libs` and `./ELT_Project/EltCore-1.16/build/libs`

1. 克隆ELT本体：https://github.com/MoegTech/EnergyLevelTransition.git

2. 克隆ELTCore：https://github.com/MoegTech/ELTCore.git

3. 你现在应该有两个下载好的压缩包，解压后将两个文件夹命名为ELT-1.16和EltCore-1.16

4. 创建一个新文件夹，命名为ELT_Project，这将是整个ELT的项目目录

5. 将ELT-1.16和EltCore-1.16文件夹放入ELT_Project目录下

6. 使用IntelliJ或者Eclipse的Import功能，选择ELT文件夹内的build.gradle文件，导入项目。（Eclipse在进入后可能要使用gradlew eclipse）

7. 等待Gradle项目同步完成

8. 在ELT目录下运行gradlew genSources生成MC源码以供参考

9. 在ELT目录下运行gradlew build，则会在ELT_Project/ELT-1.16/build/libs/和EltCore-1.16/build/lib生成两个模组的jar
