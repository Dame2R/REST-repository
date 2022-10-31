import axios from 'axios';
import create from "zustand";

type DataStoreState = {
    randomCreditCardData: string;
    getRandomCreditCard: Function;
};

export const useDataStore = create<DataStoreState>((set) => ({
    randomCreditCardData: "",
    getRandomCreditCard: () => {
        axios.get(`https://random-data-api.com/api/v2/credit_cards`)
        .then(res => {
            console.log(res)
            set({ randomCreditCardData: res.data })
        })    }
}));
