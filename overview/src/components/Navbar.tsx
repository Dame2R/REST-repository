import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import { IconButton, Paper, Tooltip } from "@mui/material";
import logo from "../images/logo.svg";
import LogoutIcon from "@mui/icons-material/Logout";

export default function NavBar() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar
        position="static"
        style={{ background: "white", color: "black", padding: 0 }}
      >
        <Toolbar>
          <Box
            component="img"
            sx={{
              height: 80,
            }}
            alt="Logo"
            src={logo}
          />
          <Box width={20} />
          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1, fontWeight: "bold" }}
          >
            Übersicht
          </Typography>
          <Tooltip title="Logout">
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              sx={{ mr: 2 }}
            >
              <LogoutIcon />
            </IconButton>
          </Tooltip>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
