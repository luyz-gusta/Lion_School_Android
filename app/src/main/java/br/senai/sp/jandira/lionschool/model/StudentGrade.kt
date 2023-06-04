package br.senai.sp.jandira.lionschool.model

data class StudentGrade(
    var nome: String,
    var foto: String,
    var matricula: String,
    var status: String,
    var sexo: String,
    var curso: String,
    var dataConclusao: String,
    var nomeCurso: String,
    var sigla: String,
    var icone: String,
    var conclusao: String,
    var disciplinas: List<Disciplina>
)
