[English](README.md)  [简体中文](README-zh_cn.md)

# 能级跃迁 1.16 ![Build](https://github.com/MoegTech/EnergyLevelTransition/workflows/ELT%20Snapshot%20Build/badge.svg) [![Join our Discord!](https://img.shields.io/badge/Discord-Join%20Us-blue)](https://discord.gg/BWn6E94)

This is the development repository of the Minecraft Fabric Mod - Energy Level Transition on Minecraft 1.16

## 协议

This Mod is available under the MIT License. 

# 目录

- [能级跃迁 1.16](#能级跃迁-116)
  - [协议](#协议)
- [目录](#目录)
- [模组介绍](#模组介绍)
- [依赖](#依赖)
- [开发指南](#开发指南)
  - [准备](#准备)
  - [如何构建](#如何构建)
  
# 模组介绍

This is a Tech Mod - which illustrates Realism with hidden Magical Elements - of World Generation, Natural Environment, Player Properties, and more. 

# 依赖

- ELT Core
- Cardinal Components API
- Lib GUI
- Advanced Runtime Resource Pack

They are Jar-in-Jar with the mod so users don't need to download them separately. 

# 开发指南

## 准备

For setup instructions of Fabric Mod please see the [fabric wiki page](https://fabricmc.net/wiki/tutorial:setup) that relates to the IDE that you are using.

## 如何构建

如果你在使用 Windows，使用以下格式运行Gradle任务

```gradlew <task>```

或者你在使用 Linux/Unix/MacOS:

```./gradlew <task>```

1. 克隆ELT本体：https://github.com/MoegTech/EnergyLevelTransition.git

2. 克隆ELTCore：https://github.com/MoegTech/ELTCore.git

3. 你现在应该有两个下载好的压缩包，解压后将两个文件夹命名为`ELT-1.16`和`EltCore-1.16`

4. 创建一个新文件夹，命名为`ELT_Project`，这将是整个ELT的项目目录

5. 将`ELT-1.16`和`EltCore-1.16`文件夹放入`ELT_Project`目录下

6. 使用IntelliJ或者Eclipse的`Import`功能，选择`ELT-1.16`文件夹内的`build.gradle`文件，导入项目。（Eclipse在进入后可能要使用`gradlew eclipse`）

7. 等待Gradle项目同步完成

8. 在`ELT-1.16`目录下运行`gradlew genSources`生成MC源码以供参考

9. 在`ELT-1.16`目录下运行`gradlew build`，则会在`./ELT_Project/ELT-1.16/build/libs/`和`./ELT_Project/EltCore-1.16/build/lib`生成两个模组的Artifacts。
