import { Stack } from "@mui/material";
import NavBar from "../components/Navbar";
import { useProcessStore } from "../stores/process_store";
import ProcessLandscape from "../components/ProcessLandscape";
import { useParams } from "react-router-dom";

export default function ProcessEnergySort() {
  const { energyLevel } = useParams();

  const allProcesses: any = useProcessStore((state) => state.allProcesses);

  const filteredProcesses: any = allProcesses.filter((obj: any) => {
    return obj.energySumYear <= 30;
  });

  return (
    <div>
      <Stack>
        <NavBar />
        <ProcessLandscape
          coreProcesses={filteredProcesses.filter(
            (p: any) =>  p.processType === "core"
          )}
          managementProcesses={filteredProcesses.filter(
            (p: any) =>  p.processType === "management"
          )}
          supportProcesses={filteredProcesses.filter(
            (p: any) =>  p.processType === "support"
          )}
        />
      </Stack>
    </div>
  );
}
