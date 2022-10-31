import { Box, Button, Stack } from "@mui/material";
import NavBar from "../components/Navbar";
import { useDataStore } from "../stores/data_store";

export default function Home() {
  
  const randomCreditCardData: any = useDataStore((state) => state.randomCreditCardData)
  const getRandomCreditCard = useDataStore((state) => state.getRandomCreditCard)

  
  return (
    <Stack>
      <NavBar />
      <Box height={200}/>
      <Button onClick={() => {
        getRandomCreditCard();
      }}>Get Credit Card</Button>
      <p>{randomCreditCardData.credit_card_expiry_date}</p>
      <p>{randomCreditCardData.credit_card_number}</p>
      <p>{randomCreditCardData.credit_card_type}</p>
    </Stack>
  );
}
