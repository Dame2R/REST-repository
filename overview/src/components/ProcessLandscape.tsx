import { Stack, Box } from "@mui/material";
import { Process } from "../types/Process";
import ProcessTypeRow from "./ProcessTypeRow";
import SubHeader from "./Subheader";

type ProcessLandscapeProps = {
  parentProcess?: Process;
  coreProcesses: Process[];
  managementProcesses: Process[];
  supportProcesses: Process[];
};

export default function ProcessLandscape(props: ProcessLandscapeProps) {
  return (
    <>
      <SubHeader />
      <Box padding={5}>
        <Stack spacing={5}>
          <ProcessTypeRow
            title="Management"
            processes={props.managementProcesses}
          />
          <ProcessTypeRow title="Core" processes={props.coreProcesses} />
          <ProcessTypeRow title="Support" processes={props.supportProcesses} />
        </Stack>
      </Box>
    </>
  );
}
