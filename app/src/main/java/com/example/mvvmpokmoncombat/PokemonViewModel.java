package com.example.mvvmpokmoncombat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonViewModel extends AndroidViewModel {

    Executor executor;
    Pokemon player;

    public static ThreadSafeList listaPokemon = new ThreadSafeList();

    public MutableLiveData<String> errorNombre = new MutableLiveData<>();
    public MutableLiveData<String> errorHp = new MutableLiveData<>();
    public MutableLiveData<String> errorAtk= new MutableLiveData<>();
    public MutableLiveData<String> errorDef = new MutableLiveData<>();
    public MutableLiveData<String> errorAtkEsp = new MutableLiveData<>();
    public MutableLiveData<String> errorDefEsp = new MutableLiveData<>();
    public MutableLiveData<Boolean> pValidado = new MutableLiveData<>();
    public MutableLiveData<Pokemon> pokemon = new MutableLiveData<>();


    public PokemonViewModel(@NonNull Application application) {
        super(application);
        executor = Executors.newSingleThreadExecutor();
        player = new Pokemon();
    }

    public void crearPokemon(String nombre, int hp, int atk, int def, int atkEsp, int defEsp){

        executor.execute(() -> player.crearPokemon(nombre, hp, atk, def, atkEsp, defEsp, new Pokemon.Callback() {
            @Override
            public void cuandoHayaErrorEnNombre(String errNombre) {
                errorNombre.postValue(errNombre);
            }

            @Override
            public void cuandoHayaErrorEnVida(String errHp) {
                errorHp.postValue(errHp);
            }

            @Override
            public void cuandoHayaErrorEnAtaque(String errAtk) {
                errorAtk.postValue(errAtk);
            }

            @Override
            public void cuandoHayaErrorEnDefensa(String errDef) {
                errorDef.postValue(errDef);
            }

            @Override
            public void cuandoHayaErrorEnAtaqueEspecial(String errAtkEsp) {
                errorAtkEsp.postValue(errAtkEsp);
            }

            @Override
            public void cuandoHayaErrorEnDefensaEspecial(String errDefEsp) {
                errorDefEsp.postValue(errDefEsp);
            }

            @Override
            public void cuandoTermineDeValidar(boolean validado, Pokemon pokemonV) {
                pValidado.postValue(validado);
                pokemon.postValue(pokemonV);
                errorNombre.postValue(null);
                errorHp.postValue(null);
                errorAtk.postValue(null);
                errorDef.postValue(null);
                errorAtkEsp.postValue(null);
                errorDefEsp.postValue(null);
            }
        }));
    }
}

