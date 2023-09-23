function GeraMatricula() {
	let dataAtual = new Date();
	let anoAtual = dataAtual.getFullYear();
	let txt = "SENAC";
	let aleatorio = Math.floor(Math.random() * 1000000); document.getElementById('matricula').value = (txt + anoAtual + aleatorio);
}