/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.maku.framework.mybatis.dao.BaseDao
 *  org.apache.ibatis.annotations.Mapper
 */
package com.jun.plugin.online.dao;

import java.util.List;

import com.jun.plugin.online.common.BaseDao;
import com.jun.plugin.online.entity.OnlineTableColumnEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OnlineTableColumnDao
extends BaseDao<OnlineTableColumnEntity> {
    public List<OnlineTableColumnEntity> getByTableId(String var1);
}

