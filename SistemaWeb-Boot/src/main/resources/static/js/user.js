/*<![CDATA[*/
		$(document).ready(function() {
			window.onload = function(){ 
				var myPromise = new Promise(function(resolve, reject) {
                   
                   resolve(cabecera());
                });
                myPromise.then(function(data, textStatus){
                    $("#IdFabrica").val(data.idFabrica);
                    $("#Cabecera").html("<p><h2>"+data.idFabrica+" - "+data.nombreFabrica+"</h2></p><p><h4>Ubicaci&oacute;n: "+data.ubicacion+", Lider: "+data.lider+"</h4></p>");
                })
                .then(function(){
                    TablaSucursal();
                    TablaVentas();
                    $.notify.defaults({position:"bottom center"});
                })
                .catch(function(error) {
                        console.log(error);
                });
				
			};
            
			function cabecera(){
				var lider = document.getElementById("lider_name").innerHTML
				return $.ajax({	
    						type: "GET",
    						dataType: "json",
    						url: "/fabrica/get-by-lider?lider="+lider+"", 
						})  
    					.done(function( data, textStatus, jqXHR ) {
						})
    					.fail(function( jqXHR, textStatus, errorThrown ) {
    						console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
    					});
			}

			function TablaSucursal() { //-------------------Dibujar la tabla sucursal al cargar la pagina---------------
			 var idFabrica = document.getElementById("IdFabrica").value;
				$.ajax({
    				type: "GET",
    				dataType: "json",
    				url: "/Sucursal/sucursal_by_id?id="+idFabrica+"",
					})  
    			.done(function( data, textStatus, jqXHR ) {
        			var table = $("#tabla_sucursales").DataTable({
                        "dom": 'Bfrtip',
                        "buttons": [
                            {
                                extend: 'excelHtml5',
                                text: '<button type="button" class="btn btn-success btn-lg"><b><i class="fa fa-file-excel-o"></i> Excel</b></button>',
                                titleAttr: 'Excel'
                            },
                            {
                                extend: 'pdfHtml5',
                                text: '<button type="button" class="btn btn-success btn-lg"><b><i class="fa fa-file-pdf-o"></i> PDF</b></button>',
                                titleAttr: 'PDF'
                            } 
                            ],
						"destroy" : true,
        				"lengthChange": true,
        				"responsive": true,
        				"autoWidth": false,
        				"pageLength": 10,
        				"dataType":"JSON",
						"data":data,
						"columns":[
							{"data":"idPunto"},
							{"data":"nombrePunto"},
							{"data":"ubicacion"},
							{"data":"idFabrica"},
							{"defaultContent":'<button type="button" class="editar btn btn-info" data-toggle="modal" data-target="#editModalSuc"><span class="glyphicon glyphicon-pencil"></span></button><button type="button" class="eliminar btn btn-danger" data-toggle="modal" data-target="#deleteModalSuc"><span class="glyphicon glyphicon-trash"></span></button>'}
							]
						});
					obtener_data_editar("#tabla_sucursales tbody",table);
					obtener_data_eliminar("#tabla_sucursales tbody",table);
    			})
    			.fail(function( jqXHR, textStatus, errorThrown ) {
    				console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
				});
			}//------------------------------fin tabla sucursales----------------------------------

			var obtener_data_editar = function(tbody,table){ //---boton editar sucursales----------
			
				$(tbody).on("click","button.editar", function(){
					var data= table.row($(this).parents("tr")).data();
					var nombrePunto=$("#nombreSuc_edit").val(data.nombrePunto),
					idPunto=$("#idSuc_edit").val(data.idPunto),
					idFabrica=$("#idFab_edit").val(data.idFabrica),
					ubicacion=$("#ubicacionSuc_edit").val(data.ubicacion);
				});
			}	//------------------------------fin boton editar sucursales-------------------------

			var obtener_data_eliminar = function(tbody,table){ //---------------------boton eliminar sucursal---------------
				$(tbody).on("click","button.eliminar", function(){
					var data= table.row($(this).parents("tr")).data();
					var idfabrica=$("#id_eliminarSuc").val(data.idPunto);
				});
			} //--------------------------------------boton eliminar sucursal-------------------------------------

			$("#eliminar_Suc").click(function(){ //-----------------------eliminar sucursal ajax-----------------------
				var id = document.getElementById("id_eliminarSuc").value;

				$.ajax({
					contentType:'application/json; charset=utf-8',
    				type: "GET",
    				dataType: 'text',
    				url: "/ventas/delete_sucursal?id="+id+"",
				})  
    			.done(function( data, textStatus, jqXHR ) {
    				})
    			.fail(function( jqXHR, textStatus, errorThrown ) {
     				console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
     			});

				$.ajax({
					contentType:'application/json; charset=utf-8',
    				type: "GET",
    				dataType: 'text',
    				url: "/Sucursal/delete?id="+id+"",
				})  
    			.done(function( data, textStatus, jqXHR ) {
    				$("#id_eliminarSuc").val("");
                    MostrarSucursales();
                    MostarVentas();
                    $.notify("Se ha eliminado la sucursal","success");
    				})
    			.fail(function( jqXHR, textStatus, errorThrown ) {
     				console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
     			});

				return false;
			}); //-----------------------------------eliminar sucursal_ajax-----------------------------------

			$("#form_editSuc").submit(function(){ // ---------------------------editar sucursal-----------------------------
				var NombreSucursal = document.getElementById("nombreSuc_edit").value;
				var Ubicacion = document.getElementById("ubicacionSuc_edit").value;
				var IdSuc = document.getElementById("idSuc_edit").value;
				var IdFab = document.getElementById("idFab_edit").value;
				
				$.ajax({
					contentType:'application/json; charset=utf-8',
    				data:  JSON.stringify({"idFabrica":""+IdFab+"","nombrePunto":""+NombreSucursal+"","ubicacion":""+Ubicacion+"","idPunto":""+IdSuc+""}),
    				type: "POST",
    				dataType: 'json',
    				url: "/Sucursal/update", 
				})  
    			.done(function( data, textStatus, jqXHR ) {
    				$("#nombreSuc_edit").val("");
    				$("#ubicacionSuc_edit").val("");
    				$("#idSuc_edit").val("");
    				$("#idFab_edit").val("");
                    MostrarSucursales();
                    $.notify("Se ha actualizado la sucursal","success");
    			})
    			.fail(function( jqXHR, textStatus, errorThrown ) {
     				console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
     			});
				return false;
    		}); //---------------------------------------- fin editar sucursal---------------------------------------

    		$("#form_savesucursal").submit(function(){ // -----------------------------Guardar sucursal---------------------------
    			var idfab=document.getElementById("IdFabrica").value;
				var NombreSucursal = document.getElementById("nombreSuc").value;
				var Ubicacion = document.getElementById("ubicacionSuc").value;
			
				$.ajax({
					contentType:'application/json; charset=utf-8',
    				data:  JSON.stringify({"nombrePunto":""+NombreSucursal+"","ubicacion":""+Ubicacion+"","idFabrica":""+idfab+""}),
    				type: "POST",
    				dataType: 'json',
    				url: "/Sucursal/save",
					})  
    				.done(function( data, textStatus, jqXHR ) {
    					$("#nombreSuc").val("");
    					$("#ubicacionSuc").val("");
                        MostrarSucursales();
                        $.notify("Sucursal guardada","success");
    				})
    				.fail(function( jqXHR, textStatus, errorThrown ) {
     					console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
     				});
				return false;
			}); //---------------------------------------fin guardar sucursal------------------------------------

			function TablaVentas() { //-------------------Dibujar la tabla ventas al cargar la pagina---------------
			     var idFabrica = document.getElementById("IdFabrica").value;
				$.ajax({
    				type: "GET",
    				dataType: "json",
    				url: "/ventas/ventas_by_id?id="+idFabrica+"",
					})  
    			.done(function( data, textStatus, jqXHR ) {
        			var table = $("#contenidoVenta").DataTable({
                        "dom": 'Bfrtip',
                        "buttons": [
                            {
                                extend: 'excelHtml5',
                                text: '<button type="button" class="btn btn-success btn-lg"><b><i class="fa fa-file-excel-o"></i> Excel</b></button>',
                                titleAttr: 'Excel'
                            },
                            {
                                extend: 'pdfHtml5',
                                text: '<button type="button" class="btn btn-success btn-lg"><b><i class="fa fa-file-pdf-o"></i> PDF</b></button>',
                                titleAttr: 'PDF'
                            } 
                            ],
						"destroy" : true,
        				"lengthChange": true,
        				"responsive": true,
        				"autoWidth": false,
        				"pageLength": 10,
        				"dataType":"JSON",
						"data":data,
						"columns":[
							{"data":"idVenta"},
							{"data":"cantidad"},
							{"data":"fecha"},
							{"data":"idPunto"},
							{"data":"idFabrica"},
							{"defaultContent":'<button type="button" class="editarVenta btn btn-info" data-toggle="modal" data-target="#editModalVenta"><span class="glyphicon glyphicon-pencil"></span></button><button type="button" class="eliminarventa btn btn-danger" data-toggle="modal" data-target="#deleteVenta"><span class="glyphicon glyphicon-trash"></span></button>'}
							]
						});
        			obtener_venta_editar("#contenidoVenta tbody",table);
					obtener_venta_eliminar("#contenidoVenta tbody",table);
    			})
    			.fail(function( jqXHR, textStatus, errorThrown ) {
     				console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
				});
			}//------------------------------------------fin tabla ventas-------------------------------------------------
			var obtener_venta_editar = function(tbody,table){ //---boton editar ventas----------
			
				$(tbody).on("click","button.editarVenta", function(){
					var data= table.row($(this).parents("tr")).data();
					var idVenta=$("#idVenta_edit").val(data.idVenta),
					cantidad=$("#cantidadVenta_editar").val(data.Cantidad),
					idFabrica=$("#Fab_edit").val(data.idFabrica);
					selector("sucursales_editar");
				});
			}	//------------------------------fin boton editar ventas-------------------------

			var obtener_venta_eliminar = function(tbody,table){ //---------------------boton eliminar venta---------------
				$(tbody).on("click","button.eliminarventa", function(){
					var data= table.row($(this).parents("tr")).data();
					var idfabrica=$("#idventa_eliminar").val(data.idVenta);
				});
			} //--------------------------------------boton eliminar venta-------------------------------------

			$("#eliminar_venta").click(function(){ //-...........................-eliminar venta ajax-----------------------
				var id = document.getElementById("idventa_eliminar").value;

				$.ajax({
					contentType:'application/json; charset=utf-8',
    				type: "GET",
    				dataType: 'text',
    				url: "/ventas/delete?id="+id+"",
				})  
    			.done(function( data, textStatus, jqXHR ) {
                    $("#idventa_eliminar").val("");
                    MostarVentas();
                    $.notify("Se ha eliminado la venta","success");
    				})
    			.fail(function( jqXHR, textStatus, errorThrown ) {
     				console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
     			});
     			return false;
    		});//----------------------------------------------eliminar venta ajax------------------------------------------------

			function selector(select){//-----llenado de select
                var idFabrica = document.getElementById("IdFabrica").value;
    			$.ajax({	
    				type: "GET",
    				dataType: "json",
    				url: "/Sucursal/sucursal_by_id?id="+idFabrica+"", 
				})  
    			.done(function( data, textStatus, jqXHR ) {
    				var selector=document.getElementById(select);
    				var i;
    				for(i = 0; i< data.length; i++){
    					selector.options[i] = new Option(""+data[i].idPunto+"",data[i].idPunto); 
    				}
    			})
    			.fail(function( jqXHR, textStatus, errorThrown ) {
    				console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
    			});
    		
    		}//--------------------------fin llenado de select-----------------------------

    		$("#Crear_Venta").click(function(){//-----------llenado de select crear-----
    			selector("sucursales");
    		});//-----------------------fin de llenado select crear--------------------------

    		$("#form_saveVenta").submit(function(){ // -----------------------------Guardar venta---------------------------
    			var idfab=document.getElementById("IdFabrica").value;
				var Fecha = document.getElementById("fecha_Venta").value;
				var Cantidad = document.getElementById("cantidadVenta").value;
				var idsucursal = document.getElementById("sucursales").value;
			
				$.ajax({
					contentType:'application/json; charset=utf-8',
    				data:  JSON.stringify({"cantidad":""+Cantidad+"","fecha":""+Fecha+"","idFabrica":""+idfab+"","idPunto":""+idsucursal+""}),
    				type: "POST",
    				dataType: 'json',
    				url: "/ventas/save",
					})  
    				.done(function( data, textStatus, jqXHR ) {
    					$("#cantidadVenta").val("");
    					$("#fecha_Venta").val("");
                        MostarVentas();
                        $.notify("Venta guardada","success");
    				})
    				.fail(function( jqXHR, textStatus, errorThrown ) {
     					console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
     				});
				return false;
			}); //---------------------------------------fin guardar venta------------------------------------

			$("#form_editVenta").submit(function(){ // ---------------------------editar venta-----------------------------
				var idVenta= document.getElementById("idVenta_edit").value;
				var idFab = document.getElementById("Fab_edit").value;
				var Cantidad = document.getElementById("cantidadVenta_editar").value;
				var fecha = document.getElementById("fecha_VentaEditar").value;
				var idSucursal = document.getElementById("sucursales_editar").value;
				
				$.ajax({
					contentType:'application/json; charset=utf-8',
    				data:  JSON.stringify({"cantidad":""+Cantidad+"","fecha":""+fecha+"","idFabrica":""+idFab+"","idPunto":""+idSucursal+"","idVenta":""+idVenta+""}),
    				type: "POST",
    				dataType: 'json',
    				url: "/ventas/update", 
				})  
    			.done(function( data, textStatus, jqXHR ) {
    				$("#idVenta_edit").val("");
    				$("#Fab_edit").val("");
    				$("#cantidadVenta_editar").val("");
    				$("#fecha_VentaEditar").val("");
                    MostarVentas();
                    $.notify("Se ha actualizado la venta","success");
    			})
    			.fail(function( jqXHR, textStatus, errorThrown ) {
     				console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
     			});
				return false;
    		}); //---------------------------------------- fin editar venta---------------------------------------


			function Grafico(fecha1,fecha2){ //--------------------------------------Grafico-------------------------------------
				var idFabrica = document.getElementById("IdFabrica").value;
                var myPromise = new Promise(function(resolve, reject) {
                    $.ajax({
                        type: "GET",
                        dataType: "json",
                        url: "/Sucursal/sucursal_by_id?id="+idFabrica+"",
                        })  
                    .done(function( data, textStatus, jqXHR ) {
                        var datos=new Array(data.length);
                        var i;
                        for( i = 0; i < data.length; i ++){
                            datos[i]= new Array(3);
                        }

                        for( i=0; i < data.length; i ++){
                            datos[i][0]=data[i].idPunto;
                            datos[i][1]=data[i].nombrePunto;
                            datos[i][2]=0;
                        }
                        resolve(datos);
                    })
                    .fail(function( jqXHR, textStatus, errorThrown ) {
                        console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
                        reject("error");
                    });
                });	

                myPromise.then(function(matriz){
                    $.ajax({
                        type: "GET",
                        dataType: "json",
                        url: "/ventas/all_date_id?fecha1="+fecha1+"&fecha2="+fecha2+"&id="+idFabrica+"",
                        })  
                    .done(function( data, textStatus, jqXHR ) {
                        var i;
                        var j;
                        for(i=0; i < data.length; i++){
                            for(j=0; j < matriz.length; j++){
                                if(data[i].idPunto == matriz[j][0]){
                                    matriz[j][2]= matriz[j][2] + data[i].cantidad;
                                }
                            }
                        }
                        var etiqueta=new Array(matriz.length);
                        var valores=new Array(matriz.length);
                        for(i=0;i<matriz.length;i++){
                            etiqueta[i]=matriz[i][1];
                            valores[i]=matriz[i][2];
                        }
                            
                        renderChart(etiqueta,valores);
                    })
                    .fail(function( jqXHR, textStatus, errorThrown ) {
                        console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
                    });
                })
                .catch(function(error) {
                    console.log(error);
                });
                    

    			function renderChart (etiqueta, valores) {
    				var color=['#DF0101','#F4FA58','#82FA58','#01DFA5','#2E64FE','#CC2EFA','#DF01A5','#FE9A2E','#FA5882','#81F7D8'];
                            var colorhigh=['#FE2E2E','#FFFF00','#40FF00','#58FAD0','#013ADF','#8904B1','#FE2EC8','#DF7401','#FE2E64','#A9F5E1'];
                            var i;
                            var pieData=[];
                            for( i=0; i<etiqueta.length; i++ ){
                                if(i<10)
                                    pieData.push({value: valores[i],color:color[i], highlight: colorhigh[i],label: etiqueta[i]});
                                else
                                    pieData.push({value: valores[i],color:color[i-10], highlight: colorhigh[i-10],label: etiqueta[i]});
                            }
                        $('#myChart').remove();
                        $('#div_canvas').append('<canvas id="myChart" width="900px" height="500px"></canvas>'); 
                        var canvas = document.getElementById("myChart");    
                        var ctx = canvas.getContext("2d");
                        window.myPie = new Chart(ctx).Pie(pieData); 
					}
    		
			}// ---------------------------------- fin grafico ----------------------------------------------------

			$("#Crear_Grafico").click(function(){ //----------------funcion boton crear grafico------------------
				var fecha1=document.getElementById("fecha1").value;
				var fecha2=document.getElementById("fecha2").value;
				if(fecha1=="" || fecha2==""){//vacios
					var hoy = new Date();
					var dd = hoy.getDate();
					var mm = hoy.getMonth()+1; //hoy es 0!
					var yyyy = hoy.getFullYear();

					if(dd<10) {
    					dd='0'+dd
					} 

					if(mm<10) {
    					mm='0'+mm
					} 

					var fecha = yyyy+'-'+mm+'-'+dd;
					Grafico(fecha,fecha);
				}else{
					Grafico(fecha1,fecha2);
				}
				
			});//-----------------------------------fin funcion boton crear grafico---------------------------------------------

		});

        function MostarVentas(){
            var idFabrica = document.getElementById("IdFabrica").value;
            $.ajax({ 
                type: "GET",
                dataType: "json",
                url: "/ventas/ventas_by_id?id="+idFabrica+"", 
            })  
            .done(function( data, textStatus, jqXHR ) {
                var table = $("#contenidoVenta").DataTable();
                table.rows().remove();
                table.rows.add(data); 
                table.draw();
            })
            .fail(function( jqXHR, textStatus, errorThrown ) {
                console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
            });
        }

        function MostrarSucursales(){
            var idFabrica = document.getElementById("IdFabrica").value;
            $.ajax({    
                type: "GET",
                dataType: "json",
                url: "/Sucursal/sucursal_by_id?id="+idFabrica+"",
            })  
            .done(function( data, textStatus, jqXHR ) {
                var table = $("#tabla_sucursales").DataTable();
                table.rows().remove();
                table.rows.add(data);
                table.draw();
            })
            .fail(function( jqXHR, textStatus, errorThrown ) {
                console.log("jqxhr: "+jqXHR+", Text Status: "+textStatus+", errorThrown: "+errorThrown+"","error");
            });
        }
	/*]]>*/