Ext.onReady(function() {
	var takeOutOrderDss = new Ext.data.GroupingStore({
		proxy : new Ext.data.HttpProxy({
			url : BASE_URL + LOGIN_ACTION.ORDER_TAKEOUT
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalCount',
			root : 'list',
			id : 'id',
			successProperty : '@success',
			fields : [ 'orderId', 'companyName', 'addTime', 'orderNo', 'orderStatus', 
				'userPhone','userName', 'userAddress', 'runName', 'runPhone',
				'total', 'discount', 'pay' ]
		}),
	});
	
	var takeOutOrderCbsm = new Ext.grid.CheckboxSelectionModel();
	var takeOutOrderCm = new Ext.grid.ColumnModel([
		takeOutOrderCbsm, {
			header : 'id',
			dataIndex : 'orderId',
			locked : true,
			hidden : true,
			width : (document.body.clientWidth - 193) / 18
		}, {
			header : '商家名称',
			dataIndex : 'companyName',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '下单时间',
			dataIndex : 'addTime',
			width : (document.body.clientWidth - 193) / 10,
		}, {
			header : '订单号',
			dataIndex : 'orderNo',
			width : (document.body.clientWidth - 193) / 10,
		}, {
			header : '订单状态',
			dataIndex : 'orderStatus',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '订单总价',
			dataIndex : 'total',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '优惠金额',
			dataIndex : 'discount',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '实付金额',
			dataIndex : 'pay',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '用户地址',
			dataIndex : 'userAddress',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '用户联系方式',
			dataIndex : 'userPhone',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '用户姓名',
			dataIndex : 'userName',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '骑手姓名',
			dataIndex : 'runName',
			width : (document.body.clientWidth - 193) / 18,
		}, {
			header : '骑手联系方式',
			dataIndex : 'runPhone',
			width : (document.body.clientWidth - 193) / 18,
		}
	]);


	var takeOutOrderMangerGrid = new Ext.grid.EditorGridPanel({
		ds : takeOutOrderDss,
		cm : takeOutOrderCm,
		sm : takeOutOrderCbsm,
		closable : true,
		border : false,
		id : 'takeOutOrderManager',
		stripeRows : true,
		height : document.body.clientHeight - 106,
		bbar : new Ext.PagingToolbar({
			store : takeOutOrderDss,
			displayInfo : true,
			displayMsg : ' 显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : '没有记录'
		}),
		tbar : [ '订单号', '-', new Ext.form.TextField({
			width : 100,
			id : 'orderNo'
		}),'商家id', '-', new Ext.form.TextField({
			width : 100,
			id : 'companyId'
		}), '&nbsp;', {
			text : '查询',
			iconCls : 'addUser',
			handler : function() {
				takeOutOrderDss.baseParams = {
					orderNo : Ext.getCmp('orderNo').getValue(),
					companyId : Ext.getCmp('companyId').getValue(),
					start : 0,
					limit : 20
				};
				
				takeOutOrderDss.load({
					params : {
						orderNo : Ext.getCmp('orderNo').getValue(),
						companyId : Ext.getCmp('companyId').getValue(),
						start : 0,
						limit : 20
					}
				});
			}
		}
		]
	});

	takeOutOrderDss.load({
		params : {
			start : 0,
			limit : 20
		}
	});

	takeOutOrderMangerGrid.render('grid-takeOutOrderManager');
});