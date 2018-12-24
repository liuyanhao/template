<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="order_info/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ORDER_INFO_ID" id="ORDER_INFO_ID" value="${pd.ORDER_INFO_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商户订单号:</td>
								<td><input type="text" name="OUT_TRADE_NO" id="OUT_TRADE_NO" value="${pd.OUT_TRADE_NO}" maxlength="32" placeholder="这里输入商户订单号" title="商户订单号" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td><input type="number" name="ORDER_STATUS" id="ORDER_STATUS" value="${pd.ORDER_STATUS}" maxlength="32" placeholder="这里输入状态" title="状态" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单金额:</td>
								<td><input type="number" name="TOTAL_FEE" id="TOTAL_FEE" value="${pd.TOTAL_FEE}" maxlength="32" placeholder="这里输入订单金额" title="订单金额" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">支付方式:</td>
								<td><input type="text" name="PAY_TYPE" id="PAY_TYPE" value="${pd.PAY_TYPE}" maxlength="32" placeholder="这里输入支付方式" title="支付方式" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">支付账户:</td>
								<td><input type="text" name="PAY_ACCOUNT" id="PAY_ACCOUNT" value="${pd.PAY_ACCOUNT}" maxlength="255" placeholder="这里输入支付账户" title="支付账户" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">下单时间:</td>
								<td><input class="span10 date-picker" name="PAY_TIME" id="PAY_TIME" value="${pd.PAY_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="支付时间" title="支付时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公众账号ID:</td>
								<td><input type="text" name="APPID" id="APPID" value="${pd.APPID}" maxlength="32" placeholder="这里输入公众账号ID" title="公众账号ID" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">商户号:</td>
								<td><input type="text" name="MCH_ID" id="MCH_ID" value="${pd.MCH_ID}" maxlength="255" placeholder="这里输入商户号" title="商户号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">签名类型:</td>
								<td><input type="text" name="SIGN_TYPE" id="SIGN_TYPE" value="${pd.SIGN_TYPE}" maxlength="32" placeholder="这里输入签名类型" title="签名类型" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">货币类型:</td>
								<td><input type="text" name="FEE_TYPE" id="FEE_TYPE" value="${pd.FEE_TYPE}" maxlength="16" placeholder="这里输入货币类型" title="货币类型" style="width:98%;"/></td>
							</tr>

							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">交易开始时间:</td>
								<td><input class="span10 date-picker" name="TIME_START" id="TIME_START" value="${pd.TIME_START}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="交易开始时间" title="交易开始时间" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">交易结束时间:</td>
								<td><input class="span10 date-picker" name="TIME_EXPIRE" id="TIME_EXPIRE" value="${pd.TIME_EXPIRE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="交易结束时间" title="交易结束时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">终端IP:</td>
								<td><input type="text" name="SPBILL_CREATE_IP" id="SPBILL_CREATE_IP" value="${pd.SPBILL_CREATE_IP}" maxlength="32" placeholder="这里输入终端IP" title="终端IP" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">授权码:</td>
								<td><input type="text" name="AUTH_CODE" id="AUTH_CODE" value="${pd.AUTH_CODE}" maxlength="128" placeholder="这里输入授权码" title="授权码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">随机字符串:</td>
								<td><input type="text" name="NONCE_STR" id="NONCE_STR" value="${pd.NONCE_STR}" maxlength="32" placeholder="这里输入随机字符串" title="随机字符串" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单优惠标记:</td>
								<td><input type="text" name="GOODS_TAG" id="GOODS_TAG" value="${pd.GOODS_TAG}" maxlength="32" placeholder="这里输入订单优惠标记" title="订单优惠标记" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td colspan="3"><input type="text" name="BZ" id="BZ" value="${pd.BZ}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->

<c:if test="${'edit' == msg }">
	<div>
		<iframe name="treeFrame" id="treeFrame" frameborder="0" src="<%=basePath%>/order_infomx/list.do?ORDER_INFO_ID=${pd.ORDER_INFO_ID}" style="margin:0 auto;width:805px;height:368px;;"></iframe>
	</div>
</c:if>

<footer>
<div style="width: 100%;padding-bottom: 2px;" class="center">
	<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
	<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
</div>
</footer>

	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#OUT_TRADE_NO").val()==""){
				$("#OUT_TRADE_NO").tips({
					side:3,
		            msg:'请输入商户订单号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OUT_TRADE_NO").focus();
			return false;
			}
			if($("#ORDER_STATUS").val()==""){
				$("#ORDER_STATUS").tips({
					side:3,
		            msg:'请输入订单状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ORDER_STATUS").focus();
			return false;
			}
			if($("#TOTAL_FEE").val()==""){
				$("#TOTAL_FEE").tips({
					side:3,
		            msg:'请输入订单金额',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TOTAL_FEE").focus();
			return false;
			}
			if($("#BZ").val()==""){
				$("#BZ").tips({
					side:3,
		            msg:'请输入备注',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BZ").focus();
			return false;
			}
			if($("#PAY_TYPE").val()==""){
				$("#PAY_TYPE").tips({
					side:3,
		            msg:'请输入支付方式',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PAY_TYPE").focus();
			return false;
			}
			if($("#PAY_TIME").val()==""){
				$("#PAY_TIME").tips({
					side:3,
		            msg:'请输入支付时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PAY_TIME").focus();
			return false;
			}
			if($("#PAY_ACCOUNT").val()==""){
				$("#PAY_ACCOUNT").tips({
					side:3,
		            msg:'请输入支付账户',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PAY_ACCOUNT").focus();
			return false;
			}
			if($("#APPID").val()==""){
				$("#APPID").tips({
					side:3,
		            msg:'请输入公众账号ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#APPID").focus();
			return false;
			}
			if($("#MCH_ID").val()==""){
				$("#MCH_ID").tips({
					side:3,
		            msg:'请输入商户号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MCH_ID").focus();
			return false;
			}
			if($("#SIGN_TYPE").val()==""){
				$("#SIGN_TYPE").tips({
					side:3,
		            msg:'请输入签名类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SIGN_TYPE").focus();
			return false;
			}
			if($("#FEE_TYPE").val()==""){
				$("#FEE_TYPE").tips({
					side:3,
		            msg:'请输入货币类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#FEE_TYPE").focus();
			return false;
			}
			if($("#GOODS_TAG").val()==""){
				$("#GOODS_TAG").tips({
					side:3,
		            msg:'请输入订单优惠标记',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GOODS_TAG").focus();
			return false;
			}
			if($("#SPBILL_CREATE_IP").val()==""){
				$("#SPBILL_CREATE_IP").tips({
					side:3,
		            msg:'请输入终端IP',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SPBILL_CREATE_IP").focus();
			return false;
			}
            if($("#TIME_START").val()==""){
                $("#TIME_START").tips({
                    side:3,
                    msg:'请输入交易开始时间',
                    bg:'#AE81FF',
                    time:2
                });
                $("#TIME_START").focus();
                return false;
            }
			if($("#TIME_EXPIRE").val()==""){
				$("#TIME_EXPIRE").tips({
					side:3,
		            msg:'请输入交易结束时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TIME_EXPIRE").focus();
			return false;
			}
			if($("#AUTH_CODE").val()==""){
				$("#AUTH_CODE").tips({
					side:3,
		            msg:'请输入授权码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#AUTH_CODE").focus();
			return false;
			}
			if($("#NONCE_STR").val()==""){
				$("#NONCE_STR").tips({
					side:3,
		            msg:'请输入随机字符串',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NONCE_STR").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>