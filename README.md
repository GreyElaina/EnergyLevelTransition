# Energy Level Transition

Minecraft Fabric 1.16.1 Mod

## Setup

For setup instructions please see the [fabric wiki page](https://fabricmc.net/wiki/tutorial:setup) that relates to the IDE that you are using.

## License

This template is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.

## 如何构建ELT项目

1. 克隆ELT本体：https://github.com/MoegTech/EnergyLevelTransition.git

2. 克隆ELTCore：https://github.com/MoegTech/ELTCore.git

3. 你现在应该有两个下载好的压缩包，解压后将两个文件夹命名为ELT和ELTCore

4. 创建一个新文件夹，命名为ELT_Project，这将是整个ELT的项目目录

5. 将ELT和ELTCore文件夹放入ELT_Project文件夹

6. 使用IntelliJ或者Eclipse的Import功能，选择ELT文件夹内的build.gradle文件，导入项目。（Eclipse在进入后可能要使用gradlew eclipse）

7. 等待Gradle项目同步完成

8. 在ELT目录下运行gradlew genSources生成MC源码以供参考

9. 在ELT目录下运行gradlew build，则会在ELT_Project/ELT/build/libs/和ELTCore/build/lib生成两个模组的jar

10. 若在ELTCore目录下运行gradlew build，则只会构建ELTCore的模组jar文件

11. gradlew runClien同理

