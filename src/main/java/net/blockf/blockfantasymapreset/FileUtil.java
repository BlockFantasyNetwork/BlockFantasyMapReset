package net.blockf.blockfantasymapreset;

import java.io.File;

public class FileUtil {
    static BlockFantasyMapReset plugin = BlockFantasyMapReset.getInstance();

    public static boolean deleteDir(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDir(f.getAbsolutePath());
                } else {
                    boolean result = f.delete();
                    if (!result)
                        plugin.getLogger().warning(String.format("删除文件 %s 发生错误",
                                f.getAbsolutePath()
                        ));
            }
    }
    return dir.delete();
}

    public static boolean copyDir(String srcPath, String targetPath) {
        File srcFolder = new File(srcPath);
        File targetFolder = new File(targetPath);
        if (!srcFolder.exists()) {
            plugin.getLogger().warning("未检测到有效备份目录,正在创建");
            if (!srcFolder.mkdir()) {
                plugin.getLogger().warning("备份目录创建失败");
                return false;
            }
            plugin.getLogger().warning("备份目录创建成功");
        }
        if (!targetFolder.exists())
            targetFolder.mkdir();
        File[] srcFiles = srcFolder.listFiles();
        if (srcFiles != null)
            for (File f : srcFiles) {
                if (f.isDirectory()) {
                    copyDir(f.getAbsolutePath(), targetPath + File.separator + f.getName());
                } else if (!copyFile(f.getAbsolutePath(), targetPath + File.separator + f.getName())) {
                    plugin.getLogger().warning(String.format("复制文件 %s 发生错误" ,
                            targetPath + File.separator + f.getName()
                    ));
                return false;
            }
    }
    return true;
            }

private static boolean copyFile(String srcFilePath, String targetFilePath) {
        File src = new File(srcFilePath);
        File target = new File(targetFilePath);
        return org.bukkit.util.FileUtil.copy(src, target);
        }
        }
