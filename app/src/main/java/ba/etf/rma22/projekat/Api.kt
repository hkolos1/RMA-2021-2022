package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.*
import retrofit2.Response
import retrofit2.http.*


interface Api {

    @GET("anketa")
    suspend fun dajSveAnkete(@Query("offset") id : Int): Response<List<Anketa>>

    @GET("anketa/{id}")
    suspend fun dajAnketu(@Path("id") id : Int) : Response<Anketa>

    @GET("grupa/{id}/ankete")
    suspend fun dajUpisaneAnkete(@Path("id") id : Int): Response<List<Anketa>>

    @GET("istrazivanje")
    suspend fun dajSvaIstrazivanja(@Query("offset") id: Int = 0) : Response<List<Istrazivanje>>

    @GET("istrazivanje/{id}")
    suspend fun dajIstrazivanje(@Path("id") id : Int) : Response<Istrazivanje>

    @GET("anketa/{id}/grupa")
    suspend fun dajDostupneGrupe(@Path("id") id : Int) : Response<List<Grupa>>

    @POST("grupa/{gid}/student/{id}")
    suspend fun upisiStudentaUGrupu(@Path("gid") gid : Int, @Path("id") id :
    String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20") : Response<UpisiUGrupuResponse>

    @GET("istrazivanje/{id}/grupa")
    suspend fun dajGrupeZaIstrazivanje(@Path("id") id : Int): Response<List<Grupa>>

    @GET("student/{id}/grupa")
    suspend fun dajStudentoveGrupe(@Path("id") id : String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20") : Response<List<Grupa>>

    @GET("grupa")
    suspend fun dajSveGrupe() : Response<List<Grupa>>

    @GET("student/{id}/anketataken/{ktid}/odgovori")
    suspend fun dajOdgovore(@Path("ktid") kid : Int, @Path("id") id :
    String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20") : Response<List<Odgovor>>

    @POST("student/{id}/anketataken/{ktid}/odgovor")
    suspend fun dodajOdgovor(@Path("id") id: String, @Path("ktid") ktid: Int, @Body o:OdgovorBody) : Response<Odgovor>

    @GET("student/{id}/anketataken")
    suspend fun dajZapocete(@Path("id") id : String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20") : Response<List<AnketaTaken>>

    @POST("student/{id}/anketa/{kid}")
    suspend fun zapocniAnketu(@Path("kid") kid : Int, @Path("id") id :
    String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20") : Response<GetTakenAnketaResponse>

    @GET("anketa/{id}/pitanja")
    suspend fun dajPitanja(@Path("id") id : Int) : Response<List<Pitanje>>

}