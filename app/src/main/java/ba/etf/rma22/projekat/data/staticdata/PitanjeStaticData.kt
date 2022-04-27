package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Pitanje

fun pitanja(): List<Pitanje>{
    return listOf(

        //Fudbal rubrika
        Pitanje("Fudbal", "Jeste li zadovoljni rezultatima Real Madrida",
            listOf("Da", "Ne", "Ne pratim Real Madrid")),

        Pitanje("Fudbal2", "Jeste li zadovoljni rezultatima Barcelone",
            listOf("Da", "Ne", "Ne pratim Barcelonu")),

        Pitanje("Fudbal3", "Jeste li zadovoljni rezultatima FC Bayerna",
            listOf("Da", "Ne", "Ne pratim Bayern")),

        Pitanje("Fudbal4", "Jeste li zadovoljni rezultatima FK Željezničar",
            listOf("Da", "Ne", "Ne pratim Željezničar")),

        Pitanje("Fudbal5", "Jeste li zadovoljni rezultatima FK Sarajevo",
            listOf("Da", "Ne", "Ne pratim Sarajevo")),

        //Košarka rubrika
        Pitanje("Košarka", "Jeste li zadovoljni rezultatima Chicago Bullsa",
            listOf("DA", "Ne", "Ne pratim NBA")),

        Pitanje("Košarka2", "Jeste li zadovoljni rezultatima Miami Heats",
            listOf("Da", "Ne", "Ne pratim NBA")),

        Pitanje("Košarka3", "Jeste li zadovoljni rezultatima Dalas Mavericksa",
            listOf("Da", "Ne", "Ne pratim NBA")),

        Pitanje("Košarka4", "Jeste li zadovoljni rezultatima BC Barcelona",
            listOf("Da", "Ne", "Ne pratim Euroligu")),

        Pitanje("Košarka5", "Jeste li zadovoljni rezultatima KK Cedevita",
            listOf("Da", "Ne", "Ne pratim KK Cedevita")),

        //Politika rubrika
        Pitanje("Politika", "Jeste li zadovoljni lokalnom upravom",
            listOf("Jesam", "Nisam", "Nemam komentar")),

        Pitanje("Politika2", "Jeste li zadovoljni sa aktuelnim predsjedništvom",
            listOf("Jesam", "Nisam", "Ne pratim politiku")),

        Pitanje("Politika3", "Da li izlazite na izbore",
            listOf("Izlazim", "Ne izlazim", "Nisam još razmislio/la")),

        //Filmovi rubrika
        Pitanje("Filmovi", "Jeste li pogledali Spider-Man: No Way Home",
            listOf("Da, jako dobar film", "Ne, ne volim taj žanr", "Nisam imao/la vremena")),

        //Muzika rubrika
        Pitanje("Muzika", "Koju vrstu muzike slušate",
            listOf("Rock", "Hip-Hop", "Folk", "Metal", "Jazz", "Rap", "Ne slušam muziku")),

        //Programer rubrika
        Pitanje("Programski", "Koja ste IT pozicija u Vašoj kompaniji",
            listOf("Full-stack Developer", "Front-end Developer", "Back-end Developer","Mobile Developer" ,"QA Engineer", "Nisam zaposlen/a")),
        )
}