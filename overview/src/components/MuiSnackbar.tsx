import { Snackbar, Button, Alert} from '@mui/material'
import { useState } from 'react'

export const MuiSnackbar = () => {
const [open, setOpen] = useState(false)
const handleClose = (
    event?: React.SyntheticEvent | Event,
    reason?: string
) => {
    if (reason === 'clickaway'){
        return 
    }
    setOpen(false)
}
return (
    <>
    <Button onClick={() => setOpen(true)}>Click on Button</Button>
    <Snackbar
        autoHideDuration={3000}
        open={open}
        onClose={handleClose}
        anchorOrigin={{
            vertical:'bottom',
            horizontal: 'center',
        }}>
            <Alert onClose={handleClose} severity="info" sx={{ width: '100%' }}>
            Dieser Prozess hat keine weiteren Teilprozesse!
  </Alert>
  </Snackbar>
  
   
    </>
)
}
