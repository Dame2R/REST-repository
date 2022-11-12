import { Stack, Grid, Box } from "@mui/material";
import NavBar from "../components/Navbar";
import { ProcessCard } from "../components/ProcessCard";
import ProcessTypeRow from "../components/ProcessTypeRow";
import SubHeader from "../components/Subheader";
import { useProcessStore } from "../stores/process_store";
import ProcessLandscape from "../components/ProcessLandscape";

export default function Home() {
  const topLevelProcesses: any = useProcessStore(
    (state) => state.topLevelProcesses
  );
  const getTopLevelProcesses = useProcessStore(
    (state) => state.getTopLevelProcesses
  );
  const coreProcesses = useProcessStore((state) => state.coreProcesses);
  const managementProcess = useProcessStore((state) => state.managementProcess);
  const supportProcess = useProcessStore((state) => state.supportProcess);

  if (topLevelProcesses.length == 0) {
    getTopLevelProcesses();
  }

  return (
    <div>
      <Stack>
        <NavBar />
        <ProcessLandscape
          coreProcesses={coreProcesses}
          managementProcesses={managementProcess}
          supportProcesses={supportProcess}
        />
      </Stack>
    </div>
  );
}
