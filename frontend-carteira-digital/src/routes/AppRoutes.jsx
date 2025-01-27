import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AccountPage from "../containers/AccountPage";
import TransferPage from "../containers/TransferPage";
import DepositPage from "../containers/DepositPage";
import HistoricTransferPage from "../containers/HistoricTransferPage";
import HomePage from "../containers/HomePage";
import GlobalStyles from "../styles/globalStyles";
import Header from "../components/Header";

const AppRoutes = () => {
    return (
        <Router>
            <GlobalStyles />
            <Header />
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/conta" element={<AccountPage />} />
                <Route path="/transferencia" element={<TransferPage />} />
                <Route path="/deposito" element={<DepositPage />} />
                <Route path="/lista-transferencia" element={<HistoricTransferPage />} />
            </Routes>
        </Router>
    );
};

export default AppRoutes;