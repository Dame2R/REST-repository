import { Card, Typography, CardContent, Box, Stack } from '@mui/material';

type ProcessCardProps = {
    process: any;
}

function colorBox(energy: number): string {
    if (energy <= 30) {
        return '#33CC00' /* green */
    }
    else if (energy > 30 && energy <= 170) {
        return '#FFEF00' /* yellow */
    }
    else if (energy > 170) {
        return '#FF3300' /* red */
    };
    return 'gray'
};

export const ProcessCard = (props: ProcessCardProps) => {

    var energySumYear = props.process.energySumYear;
    var newBackground: string = colorBox(energySumYear);

    return (
        <Card
            sx={{
                maxWidth: 300,
                minWidth: 300,
                margin: "auto",
                cursor: "pointer",
                padding: 0,
                '&:hover': {
                    boxShadow: "0 16px 70px -12.125px rgba(0,0,0,0.3)"
                },
            }}
        >
            <CardContent sx={{
                padding: 0,
                "&:last-child": {
                    paddingBottom: 0
                }
            }}>
                <Stack direction='row' justifyContent='space-between'>
                    <Typography variant='h6' fontWeight='bold' padding={3}>
                        {props.process.name}
                    </Typography>
                    <Box
                        sx={{
                            width: '25%',
                    textAlign: 'center',
                    display: 'flex',
                    justifyContent: 'center', /* align horizontal */
                    alignItems: 'center', /* align vertical */
                    fontWeight: 'bold',
                        }} bgcolor={colorBox(energySumYear)}
                    >
                    <Typography variant='h6' fontWeight='bold'>{energySumYear}</Typography>
                </Box>
            </Stack>

        </CardContent >
        </Card >
    );
}