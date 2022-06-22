package ba.etf.rma22.projekat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import java.text.SimpleDateFormat
import java.util.*


class AnketaAdapter(

    var ankete: List<Anketa>,
    private val onItemClicked: (anketa:Anketa) -> Unit
    ) : RecyclerView.Adapter<AnketaAdapter.AnketaViewHolder>() {

    fun updateAnkete(anketa: List<Anketa>) {
        this.ankete = anketa
        notifyDataSetChanged()
    }

    inner class AnketaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val status : ImageView = itemView.findViewById(R.id.statusAnkete)
        val istrazivanja: TextView = itemView.findViewById(R.id.brojIstrazivanja)
        val naziv: TextView = itemView.findViewById(R.id.nazivAnkete)
        val datum: TextView = itemView.findViewById(R.id.datumAnkete)
        val progress: ProgressBar = itemView.findViewById(R.id.progresZavrsetka)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.anketa_item, parent, false)
        return AnketaViewHolder(view)
    }

    override fun getItemCount(): Int = ankete.size
    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClicked(ankete[position])
        }

        /*Formatiranje datuma u formatu dan,mjesec,godina*/
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        holder.status.setImageResource(R.drawable.zelena)
        holder.progress.setProgress(60
            //(ankete[position].progres!!.times(100)).toInt()
        );
        holder.datum.text = "Vrijeme zatvaranja: ";

        /*---Plavi status = Anketa urađena---*/
        /*if(ankete[position].datumRada!=null){
            holder.status.setImageResource(R.drawable.plava)
            holder.progress.setProgress(((ankete[position].progres*100).toInt()));
            holder.datum.text = "Anketa urađena: " + formatter.format(ankete[position].datumRada).toString();
        }
        else if(ankete[position].datumPocetak.before(Calendar.getInstance().time) &&
            ankete[position].datumKraj.after(Calendar.getInstance().time) && ankete[position].datumRada == null){

            /*---Zeleni status = Aktivna anketa---*/
            holder.status.setImageResource(R.drawable.zelena)
            holder.progress.setProgress(((ankete[position].progres*100).toInt()));
            holder.datum.text = "Vrijeme zatvaranja: " + formatter.format(ankete[position].datumKraj).toString();
        }else if(ankete[position].datumPocetak.after(Calendar.getInstance().time) &&
            ankete[position].datumKraj.after(Calendar.getInstance().time) && ankete[position].datumRada == null){

            /*---Zuti status = Buduca anketa---*/
            holder.status.setImageResource(R.drawable.zuta)
            holder.progress.setProgress(((ankete[position].progres*100).toInt()));
            holder.datum.text = "Vrijeme aktiviranja: " + formatter.format(ankete[position].datumPocetak).toString();
        }else if(ankete[position].datumKraj.before(Calendar.getInstance().time) && ankete[position].datumRada == null){

            /*---Crveni status = Prosla anketa---*/
            holder.status.setImageResource(R.drawable.crvena)
            holder.progress.setProgress(((ankete[position].progres*100).toInt()));
            holder.datum.text = "Anketa zatvorena: " + formatter.format(ankete[position].datumPocetak).toString();
        }*/

        holder.istrazivanja.text = ankete[position].nazivIstrazivanja;
        holder.naziv.text = ankete[position].naziv;
        //holder.progress.setProgress(R.id.progresZavrsetka);
    }
}