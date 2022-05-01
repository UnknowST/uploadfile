package service;

import dao.upfile;

public interface Operate {

    int savefile(upfile file);   //保存文件原始信息
    upfile findpath(String uuid); //根据文件uuid查找原始文件信息
}
