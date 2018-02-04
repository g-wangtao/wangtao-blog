var YX = {};
//kendo表格公共方法
YX.kUI = {
	options: {
		_grid: {
			dataSource: {},
			selectable: "row",
			sortable: true,
			groupable: false,
			editable: false,
			resizable: true,
			reorderable: true,
			pageable: {
				refresh: false,
				pageSize: true,
				pageSizes: [50, 100, 200, 300]
			}
		},
		grid(confs) {
			return Object.assign(JSON.parse(JSON.stringify(this._grid)), confs || {});
		},
		_treeList: {
			resizable: true
		},
		treeList(confs) {
			return Object.assign(this._treeList, confs || {});
		}
	},
	getDataHeader(url, type, dataType, contentType) {
		return url ? {
			contentType: contentType || "application/json",
			dataType: dataType || "json",
			type: type || "POST",
			async: false,
			url: url
		} : undefined;
	},
	getGridSrc(idField, readUrl, filter) { //创建数据对象
		return new kendo.data.DataSource({
			schema: {
				// model: { id: idField },
				data: function(response) {
					return response.data || response; //<========根据字段源
				},
				total: "total"
			},
			transport: {
				parameterMap: function(options) {
					return JSON.stringify(options);
				},
				read: this.getDataHeader(readUrl)
			},
			error: function(e) {
				console.log(e)
				YX.Alert.error("系统错误！请联系管理员！");
			},
			serverPaging: true,
			serverFiltering: true,
			serverSorting: true,
			filter: filter,
			refresh: true,
			pageSize: 100
		});
	},
	getListSrc(idField, readUrl, filter) {
		return new kendo.data.DataSource({
			schema: {
				model: {
					id: idField
				},
				data: function(response) {
					return response.data || response;
				}
			},
			transport: {
				parameterMap: function(options) {
					return JSON.stringify(options);
				},
				read: this.getDataHeader(readUrl)
			},
			serverFiltering: false,
			filter: filter
		});
	},
	getTreeListSrc(idField, readUrl, parentField, expanded = true) {
		return new kendo.data.TreeListDataSource({
			schema: {
				model: {
					id: idField,
					fields: {
						parentId: {
							field: parentField,
							nullable: true
						}
					},
					expanded: expanded //<=====是否默认展开==//
				},
				data: function(response) {
					return response.data || response; //<====获取json中的行数据
				}
			},
			transport: {
				parameterMap: function(options) {
					return JSON.stringify(options);
				},
				read: this.getDataHeader(readUrl)
			},
			serverFiltering: true,
			serverSorting: true
		});
	},
	sformat(o, isr = false) { //isr=是否保留匹配k成功但是为空的对象
		var obj = new Object();
		for(var k in o) {
			if(o[k]) { //值非空
				if(k.indexOf("p_") >= 0) { //是否p_开头
					if(typeof(o[k]) == "object" && o[k].value) { //非空，与对象判断
						if(o[k].text && typeof(o[k].text) == "string") { ////非空，与值判断
							obj[o[k].text] = o[k].value;
						} else {
							if(isr) {
								obj[k] = o[k];
							}
						}
					}
					continue;
				}
				obj[k] = o[k];
			}
		}
		return obj;
	},
	simpleFilter(txt, val) {
		var filter = new Object();
		if(txt && val) {
			filter[txt] = val;
		}
		return filter;
	},
	simpleFilter2(arr, val, logic) {
		var filter = {
			logic: logic || "or",
			filters: []
		};
		var tmpItm;
		if(Object.prototype.toString.call(arr) === '[object Array]') {
			if(val) {
				for(var itm in arr) {
					if(arr.hasOwnProperty(itm)) {
						tmpItm = new Object();
						tmpItm[arr[itm]] = val;
						filter.filters.push(tmpItm);
					}
				}
			}
		} else if(typeof arr == "object") {
			for(var itm in arr) {
				var m = arr[itm];
				if(itm === "logic") {
					filter["logic"] = m;
					continue;
				}
				if($.trim(m)) {
					tmpItm = new Object();
					tmpItm[arr[itm]] = m;
					filter.filters.push(tmpItm);
				}
			}
		}
		return filter;
	},
	filterFormat(o) {
		if(typeof o != "object") return;
		var filter = {
			logic: o.logic || "and",
			filters: []
		};
		for(var i in o) {
			var m = o[i];
			if(i === "logic") {
				continue;
			}
			if($.trim(m) && (typeof m === "string" || typeof m === "number")) {
				filter.filters.push({
					field: i,
					value: $.trim(m),
					operator: "eq"
				});
			} else if(typeof m === "object" && $.trim(m.value)) {
				if(m.operator === "in") {
					filter.filters.push({
						field: i,
						value: m.value.split("/"),
						operator: m.operator || "in"
					});
				} else {
					filter.filters.push({
						field: i,
						value: $.trim(m.value),
						operator: m.operator || "eq"
					});
				}
			} else if(typeof m === "object" && m.type === "range") {
				if(m.start) filter.filters.push({
					field: i,
					value: m.start,
					operator: "gte"
				});
				if(m.end) {
					filter.filters.push({
						field: i,
						value: m.end,
						operator: "lte"
					});
				}
			}
		}
		return filter;
	},
	reSet(element, cofs) {
		if(element.data("kendoGrid")) {
			element.data("kendoGrid").destroy();
			element.empty();
		}
		element.kendoGrid(cofs);
	},
	gridSelectAll(tGrid) {
		var tmpGrid = tGrid.data("kendoGrid");
		for(var i = 0; i < tmpGrid.dataSource.data().length; i++) {
			tmpGrid.select(tmpGrid.tbody.find(">tr:not(.k-grouping-row)").eq(i));
		}
	},
	getGridSelectRows(tGrid, isg) {
		//var tmpGrid = tGrid.data(isg || "kendoGrid");
		//var rows = tmpGrid.select(), selecteds = [];
		//if (rows.length > 0) {
		//    rows.each(function (i, row) {
		//        var dataItem = tmpGrid.dataItem(row);
		//        if (dataItem) {
		//            selecteds.push(dataItem);
		//        }
		//    });
		//}
		return this.getGridCursorRows(tGrid.data(isg || "kendoGrid")); //selecteds;
	},
	getTreeSelectRows(tGrid) {
		return this.getGridSelectRows(tGrid, "kendoTreeList");
	},
	getGridCursorRows(tmpGrid) {
		var rows = tmpGrid.select(),
			selecteds = [];
		if(rows.length > 0) {
			rows.each(function(i, row) {
				var dataItem = tmpGrid.dataItem(row);
				if(dataItem) {
					selecteds.push(dataItem);
				}
			});
		}
		return selecteds;
	},
	//设置数据表格高度
	getContainerH() {
		var outerHeight = 0;
		$(".nav-bar").each(function(i, v) {
			outerHeight += $(v).outerHeight();
		});
		return window.innerHeight - outerHeight - 1;
	},
	//设置数据表格高度
	resize: function(containerHeight) {
		containerHeight = (containerHeight == undefined ? this.getContainerH() : containerHeight);
		$('.k-grid.dynamicHeight,.dynamic-height.k-grid').each(function() {
			var grid = $(this),
				h1 = grid.find('div.k-grid-toolbar').outerHeight() || 0,
				h2 = grid.find('div.k-grouping-header').outerHeight() || 0,
				h3 = grid.find('div.k-grid-header').outerHeight() || 0,
				h4 = grid.find('div.k-grid-pager').outerHeight() || 0,
				ch = containerHeight - 2 - h1 - h2 - h3 - h4;
			if(ch > 0) {
				grid.find('div.k-grid-content').css({
					"maxHeight": ch + 'px',
					"minHeight": (ch) + 'px'
				});
				grid.find('div.k-grid-content-locked').css({
					"maxHeight": ch + 'px',
					"minHeight": (ch) + 'px'
				});
			}
		});
	}
};
//kendo对话框公共方法
YX.KendoModal = {
	//setModal: function(html = "",lg=true, w = "auto") {//创建 设置 弹出框
	setModal: function(html, lg, w) { //创建 设置 弹出框
		if($("#modal_theme_primary").length <= 0) {
			$("body").append('<div id="modal_theme_primary" class="modal fade"><div class="modal-dialog modal-lg"><div class="modal-content"></div></div></div>');
		}
		$("#modal_theme_primary>div.modal-dialog").css({
			"min-width": w,
			"max-width": w
		});
		if(!lg) {
			$("#modal_theme_primary>div.modal-dialog.modal-lg").removeClass("modal-lg");
		}
		$("#modal_theme_primary>div.modal-dialog>div.modal-content").html(html);
		//$("#modal_theme_primary").modal({
		//    backdrop: 'static',
		//    keyboard: false
		//});
	},
	//modal: function(url, data = {},lg=true, w = "auto") { //弹出框 封装
	modal: function(url, data, lg, w) { //弹出框 封装  
		if(typeof(data) == "boolean") {
			[data, lg] = [{},
				data
			];
		}
		if(typeof(url) == "string") {
			$.ajax({
				type: "get",
				url: url,
				data: data,
				async: true,
				dataType: "html",
				success: function(data) {
					lg = lg == undefined ? true : lg;
					w = w == undefined ? "auto" : w;
					YX.KendoModal.setModal(data, lg, w);
				}
			});
		}
	}
};
//消息弹出框公共方法
YX.Alert = {
	success: function(message) {
		Vue.prototype.$notify({
			title: '成功',
			message: message,
			type: 'success'
		});
	},
	warning: function(message) {
		Vue.prototype.$notify({
			title: '警告',
			message: message,
			type: 'warning'
		});
	},
	info: function(message) {
		Vue.prototype.$notify.info({
			title: '消息',
			message: message
		});
	},
	error: function(message) {
		Vue.prototype.$notify.error({
			title: '错误',
			message: message
		});
	}
};
YX.Cascader = {
	getObj(val, opt) { //地址选择器通过取值的数组找到完整地址对象
		return val.map(function(value, index, array) {
			for(var itm of opt) {
				if(itm.value == value) {
					opt = itm.children;
					return itm;
				}
			}
			return null;
		});
	}
};
YX.Element = {
	create(i, l = "body") { //创建节点并添加
		t = i.split(".").reverse()[0];
		var n = null;
		n = document.createElement({
			"js": "script",
			"css": "link"
		}[t]);
		if(t == "js") {
			n.setAttribute("type", "text/javascript");
			n.setAttribute("src", i);
		} else if(t == "css") {
			n.setAttribute("rel", "stylesheet");
			n.setAttribute("href", i);
		}
		if(n != null) {
			// n.setAttribute("defer", "defer");
			if(l == "body") {
				document.body.appendChild(n);
			} else if(l == "head") {
				document.head.appendChild(n);
			} else {
				l.appendChild(n);
			}
		}
		return n;
	},
	addNode(o, c = undefined) { //单个添加
		var n = null;
		if(typeof(o) == "object") {
			n = this.create(o.src, (o.parent ? o.parent : "body"));
			if(typeof(o.load) == "function") {
				n.onload = o.load; //绑定加载事件
			}
			if(typeof(o.err) == "function") {
				n.onerror = o.err; //绑定错误事件
			}
		} else if(typeof(o) == "string") {
			n = this.create(o); //直接添加节点
			if(typeof(c) == "function") {
				n.onerror = n.onload = c; //绑定加载事件
			}
		}
	},
	addNodes(o, index = 0) { //多个添加
		if(o.src.length > index && typeof(o.src) == "object" && o.src.length > 0) {
			var n = this.create(o.src[index], o.parent);
			if(o.src.length - 1 == index && typeof(o.load) == "function") {
				n.onerror = n.onload = o.load;
			} else {
				n.onerror = n.onload = function() {
					addNodes(o, index + 1);
				}
			}
		}
	}
};
//YX.plug = {
//    file: ["file.plugin.js"],
//    XLSX: ["xlsx/xlsx.min.js", "file.plugin.js"]
//};
//YX.using = function () {//添加YX插件
//    var usUrl=""
//    YX.Element.addNodes()
//};
//加载遮罩层  
YX.mainLoading = {
	IsShow: false,
	show: false
};
Object.defineProperty(YX.mainLoading, "show", {
	enumerable: true,
	configurable: true,
	get: function() {
		return YX.mainLoading.IsShow;
	},
	set: function(newVal) {
		YX.mainLoading.IsShow = newVal;
		if(newVal) {
			$(".main_loading").fadeIn(100);
		} else {
			$(".main_loading").fadeOut(300);
		}
	}
});
YX.base = {
	ConvertTime(Time) {
		return Time.getFullYear() + '-' + (Time.getMonth() + 1) + '-' + Time.getDate();
	},
	ConvertTimeMin(Time) {
		return Time.getFullYear() + '-' + (Time.getMonth() + 1) + '-' + Time.getDate() + " 00:00:00";
	},
	ConvertTimeMax(Time) {
		return Time.getFullYear() + '-' + (Time.getMonth() + 1) + '-' + Time.getDate() + " 23:59:59";
	},
	formatDBTime(dbTime) {
		if(dbTime == null) return "";
		return dbTime.replace("null", "").replace("0001-01-01 00:00:00", "").replace("#", " ");
	},
	SetDTP(d, isdef) { //需要赋值对象，是否需要默认值
		if(isdef) {
			d = [new Date(), new Date()]
		}
		if(d != undefined && d != "" && d[0] != null) {
			var start = new Date(d[0]);
			var end = new Date(d[1]);
			return [YX.base.ConvertTimeMin(start), YX.base.ConvertTimeMax(end)];
		}
		return "";
	}
};
//jQuery Ajax基类
$.ajaxSetup({
	dataType: "json", //下载格式
	type: "POST", //上传数据方式
	cache: false, //是否启用浏览器缓存
	contentType: "application/json",
	beforeSend: function() {
		if(window.loadingBar) {
			loadingBar.start();
		}
	},
	complete: function(xhr, status) {
		if(window.loadingBar) {
			loadingBar.finish();
		}
		if(YX.mainLoading.show) {
			YX.mainLoading.show = false;
		}
	},
	error: function(textStatus, jqXHR) {
		if(window.loadingBar) {
			YX.Alert.error("系统错误！请联系管理员！");
			loadingBar.error();
		}
		if(YX.mainLoading.show) {
			YX.mainLoading.show = false;
		}
	}
});
/*Object*/
YX.obj = {
	reverse(obj) { //对象键值倒转
		var o = new Object();
		for(var k in obj) {
			o[obj[k]] = k;
		}
		return o;
	}
};
var objCopy = {
	deepCopy() {
		let temp = obj.constructor === Array ? [] : {}
		for(let val in obj) {
			temp[val] = typeof obj[val] == 'object' ? deepCopy(obj[val]) : obj[val];
		}
		return temp;
	},
	copy2json(o) {
		return JSON.parse(JSON.stringify(o));
	},
	arrCopy() {
		return this.concat();
	}
};
/*String*/
var str = {
	lower(s) {
		return s.toLowerCase();
	},
	upper(s) {
		return s.toUpperCase();
	},
	has(s, c) {
		return s.indexOf(c) < 0 ? false : true;
	},
	trim(s) {
		return s.replace(/(^\s+)|(\s+$)/g, '')
	}
}
YX.TypeTO = {
	//BOOL转INT
	bootToInt: function(o, l, app) {
		for(var itm of l) {
			o[itm] = Number(app[itm]) > 0 ? 1402 : 1401;
		}
	},
	//INT转BOOL
	intToBool: function(o, l, app) {
		for(var itm of l) {
			app[itm] = o[itm] == 1401 ? false : true;
		}
	},
};
Array.prototype.removeByValue = function(val) {
	for(var i = 0; i < this.length; i++) {
		if(this[i] == val) {
			this.splice(i, 1);
			break;
		}
	}
}
Array.prototype.unique = function() {
	var str = this;
	var newArr = [],
		i = 0,
		len = str.length;
	for(; i < len; i++) {
		var a = str[i];
		if(newArr.indexOf(a) !== -1) {
			continue;
		} else {
			newArr[newArr.length] = a;
		}
	}
	return newArr;
}

function fullMenuKey(l) {
	var tmp;
	for(var k of l) {
		var len = k.length / 2;
		for(var i = 1; i < len; i++) {
			tmp = k.substr(0, i * 2);
			if(l.indexOf(tmp) < 0) {
				l.push(tmp);
			}
		}
	}
	return l;
}

function smMenuKey(l) {
	var tmp;
	var templ = objCopy.copy2json(l);
	for(var k of l) {
		var len = k.length / 2;
		for(var i = 1; i < len; i++) {
			tmp = k.substr(0, i * 2);
			if(l.indexOf(tmp) >= 0) {
				templ.removeByValue(tmp);
			}
		}
	}
	return templ.unique();
}