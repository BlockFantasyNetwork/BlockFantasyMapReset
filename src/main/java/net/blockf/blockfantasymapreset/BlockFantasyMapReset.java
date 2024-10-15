package net.blockf.blockfantasymapreset;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockFantasyMapReset extends JavaPlugin {

    public static BlockFantasyMapReset instance;

    String worldName = "world";

    public void onLoad() {
        instance = this;
        getLogger().info(String.format("Step1 正在删除原地图文件,结果:%b" , FileUtil.deleteDir("world")));
        getLogger().info(String.format("Step2 正在拷贝地图文件,结果:%b", FileUtil.copyDir("world_backup", "world")));
    }

    public void onEnable() {
        World world = Bukkit.getWorld(this.worldName);
        world.setAutoSave(false);
        getLogger().info(String.format("地图 %s UID:%s",
                this.worldName,
                world.getUID()
        ));
        getLogger().info(String.format("关闭地图 %s 自动保存, 当前状态:%b",
                this.worldName,
                world.isAutoSave()
        ));
}

    public void onDisable() {}

    public static BlockFantasyMapReset getInstance() {
        return instance;
    }
}
