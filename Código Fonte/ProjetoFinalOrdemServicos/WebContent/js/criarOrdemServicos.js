//valida o cliente
function validarFormulario(){

	 var campo = document.getElementById('formulario:cliente');
	if(campo.value.length==0){
		alert('O campo cliente deve ser preenchido.');
		campo.focus();
		return false;
	}
	
	var campo = document.getElementById('formulario:cliente');
	if (campo.value.length <= 3) {
		alert("Nome muito pequeno!");
		campo.focus();
		return false;
	}
	
	var campo = document.getElementById('formulario:cliente');
	var filter = /[a-zA-Z\u00C0-\u00FF ]+/i;
	if (!campo.value.match(filter)) {
		alert("Digite apenas letras para o cliente!");
		campo.focus(); 
		return false;
	  }
	
	var campo = document.getElementById('formulario:endereco');
	if(campo.value.length==0){
		alert('O campo endereco deve ser preenchido.');
		campo.focus();
		return false;
	}
	
	var campo = document.getElementById('formulario:telefone');
	if(campo.value.length==0){
		alert('O campo telefone deve ser preenchido.');
		campo.focus();
		return false;
	}
	
	var campo = document.getElementById('formulario:dataEntrada');
	if (campo.value.length == 0) {
		alert('Digite uma data!');
		campo.focus();
		return false;
	}
	
	var campo = document.getElementById('formulario:dataEntrada');
	if (campo.value.length != 0) {
		validarData(campo.value);
		campo.focus();
	}
	
	var campo = document.getElementById('formulario:valor');
	if(campo.value == 0.0){
		alert('O campo valor deve ser preenchido.');
		campo.focus();
		return false;
	}
	return true;
}


function validarData(data){
    var expReg = /^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[1-2][0-9]\d{2})$/;
    var vdt = new Date();
    var vdia = vdt.getDay() - 1;
    var vmes = vdt.getMonth();
    var vano = vdt.getFullYear();
    if ((data.match(expReg)) && (data != '')) {
        var dia = parseFloat(data.substring(0, 2));
        var mes = parseFloat(data.substring(3, 5));
        var ano = parseFloat(data.substring(6, 10));
        if ((mes == 4 && dia > 30) || (mes == 6 && dia > 30) || (mes == 9 && dia > 30) || (mes == 11 && dia > 30)) {
            alert('O mês especificado contém no máximo 30 dias!');
            return false;
        }
        if (ano % 4 != 0 && mes == 2 && dia > 28) {
            alert('O mês especificado contém no máximo 28 dias!');
            return false;
        }
        if (ano % 4 == 0 && mes == 2 && dia > 29) {
            alert('O mês especificado contém no máximo 29 dias!');
            return false;
        }
        if (ano > vano) {
            alert('A data informada é maior que a data atual!');
            return false;
        }
        if (dia > vdia) {
        	alert ('A data informada é maior que a data atual!')
        }
    }
    else {
        alert('Data inválida!');
        return false;
    }
    
    return true
}
