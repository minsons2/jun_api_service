package com.bjc.lcp.api.component;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.db.meta.Column;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.bjc.lcp.core.api.executor.AbstractExecutor;
import com.gitthub.wujun728.engine.common.DataResult;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jun.plugin.system.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 组件ID：BAS000000000100
 * 描 述：执行指定的SQL语句（insert/update/delete/select语句）
 * 版本历史：1.0.0版 参数说明：
 * 参 数1：要执行的SQL语句
 * 参 数2：sql参数
 * 参 数3：执行SQL语句处理记录数存放标签
 *
 * 说明：需要把该代码放进DB，api_config，测试JSONOBject对象直接返回-保存在庫裡面
 */
@Component
public class RecordSaveService extends  AbstractExecutor<DataResult, Map<String,Object>>  {

	@Override
	public DataResult execute(Map<String, Object> params) throws BusinessException {
		super.initDb();
		super.setParameters(params);


		return save(params);
	}

	public DataResult save(Map<String, Object> params) throws BusinessException {
		String tableName = getPara(params,"tableName");
		Table talbe = MetaUtil.getTableMeta(Db.use(MASTER).getConfig().getDataSource(),tableName);
		Collection<Column> columns =  talbe.getColumns();
		for(Column column : columns){
			if(!column.isNullable() && !column.isAutoIncrement()){
				if(MapUtil.getStr(params,column.getName())==null){
					throw new BusinessException("参数["+column.getName() + "]不能为空！");
				}
			}
		}
		Record record = new Record();
		for(Column column : columns){
			if(!column.isAutoIncrement()){
				record.set(column.getName(), getId(MapUtil.getStr(params,column.getName())) );
			}
		}

		Boolean resutl = null;
		try {
			resutl = Db.use(MASTER).save(tableName, record);
			System.out.println("返回数据为：" + JSONUtil.toJsonStr(resutl));
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().contains("Duplicate")){
				return DataResult.fail("数据重复，主键冲突："+e.getMessage());
			}
			if(e.getMessage().contains("Incorrect datetime")){
				return DataResult.fail("数据格式有误，日期格式不规范(yyyy-mm-dd)："+e.getMessage());
			}
			if(e.getMessage().contains("Data too long")){
				return DataResult.fail("数据字段值太长，超出最大长度："+e.getMessage());
			}
//			throw new RuntimeException(e);
			return DataResult.fail(e.getMessage());
		}
		return DataResult.success(resutl);
	}

	@Override
	public DataResult rollback(Map<String, Object> parms) {
		return DataResult.success();
	}

}
