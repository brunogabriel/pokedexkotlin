package io.github.brunogabriel.pokedexkotlin.main.list

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.application.BaseActivity
import io.github.brunogabriel.pokedexkotlin.shared.application.BaseFragment
import io.github.brunogabriel.pokedexkotlin.shared.models.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.network.RetrofitManager
import io.github.brunogabriel.pokedexkotlin.shared.network.service.PokemonService
import io.github.brunogabriel.pokedexkotlin.shared.ui.bind
import org.jetbrains.anko.find

/**
 * Created by bruno on 7/25/17.
 */
class PokemonListFragment : BaseFragment(), PokemonListContract.View {

    override var presenter: PokemonListContract.Presenter? = null

    // Views
    private val recyclerView by bind<RecyclerView>(R.id.recyclerview)
    private val holderLoading by bind<View>(R.id.holder_loading)
    private val holderTryAgain by bind<View>(R.id.holder_try_again)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        holderTryAgain.find<AppCompatButton>(R.id.tryagain_button).setOnClickListener {
            holderTryAgain.visibility = View.GONE
            presenter?.start()
        }
        presenter = PokemonListPresenter(this, RetrofitManager.createService(PokemonService::class.java)!!)
        presenter?.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun showLoading() {
        holderLoading.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        holderLoading.visibility = View.GONE
    }

    override fun showNetworkUnavailable() {
        (activity as BaseActivity).showSnackbar(recyclerView, getString(R.string.network_unavailable_message))
        showTryAgain()
    }

    override fun showPokemonResource(pokemonResourceList: List<Pokemon>) {
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.addItemDecoration(object: RecyclerView.ItemDecoration(){
            override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                var convertedPixels = (activity as BaseActivity).convertDpToPixels(4)
                outRect.bottom = convertedPixels
                outRect.top = convertedPixels
                outRect.left = convertedPixels
                outRect.right = convertedPixels
            }
        })
        recyclerView.adapter = PokemonNamedAdapter(pokemonResourceList)
    }

    override fun showTryAgain() {
        holderTryAgain.visibility = View.VISIBLE
    }
}