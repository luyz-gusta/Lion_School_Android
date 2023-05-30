package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.CursoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CursoService {

    @GET("cursos")
    fun getCursos():Call<CursoList>

//    @GET("alunos?curso={nome_curso}")
}