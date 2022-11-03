import { Box, Button, Stack, Grid } from "@mui/material";
import NavBar from "../components/Navbar";
import { useDataStore } from "../stores/data_store";
import { ProcessCard } from "../components/ProcessCard";
import SubHeader from "../components/Subheader";
import mock_data from "../mock/mock_data.json";

export default function Home() {
  
  const randomCreditCardData: any = useDataStore((state) => state.randomCreditCardData);
  const getRandomCreditCard = useDataStore((state) => state.getRandomCreditCard);

  

const processes = mock_data.map((process) => (
    <Grid item xs={12} sm={12} md={6} lg={4} xl={3}>
    <ProcessCard process={process}/>
    </Grid>
));

  return (
    <div>
    <Stack>
      <NavBar />
      <SubHeader />
    </Stack>
    <Grid container spacing={3} paddingTop={10}>
      {processes}
    </Grid>
    <Button onClick={() => {
        getRandomCreditCard();
      }}>Get Credit Card</Button>
      <p>{randomCreditCardData.credit_card_expiry_date}</p>
      <p>{randomCreditCardData.credit_card_number}</p>
      <p>{randomCreditCardData.credit_card_type}</p>
    </div>
  );
}
