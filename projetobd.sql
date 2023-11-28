-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 28/11/2023 às 03:37
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `projetobd`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `aluguel`
--

CREATE TABLE `aluguel` (
  `idAluguel` int(11) NOT NULL,
  `idVeiculo` int(11) NOT NULL,
  `placaVeiculo` varchar(7) DEFAULT NULL,
  `dataAluguel` date NOT NULL,
  `dataEntrega` date NOT NULL,
  `idCliente` int(11) NOT NULL,
  `cpfCliente` varchar(14) DEFAULT NULL,
  `nomeCliente` varchar(100) DEFAULT NULL,
  `entregue` varchar(5) DEFAULT NULL,
  `observacao` varchar(30) DEFAULT NULL,
  `valorPago` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `aluguel`
--

INSERT INTO `aluguel` (`idAluguel`, `idVeiculo`, `placaVeiculo`, `dataAluguel`, `dataEntrega`, `idCliente`, `cpfCliente`, `nomeCliente`, `entregue`, `observacao`, `valorPago`) VALUES
(3, 1, 'ABC1234', '2023-11-07', '2023-11-14', 1, '01201201223', NULL, 'SIM', 'AAAA', 200),
(4, 1, 'ABC1212', '2023-11-14', '2023-11-28', 1, '02102102112', 'Paulo', 'Não', 'AAAA', 300),
(5, 1, 'ADF4321', '2023-11-24', '2023-11-10', 1, '01201221312', 'Pablo', 'N', 'NNNNNNNN', 200);

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `uf` char(2) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`id`, `login`, `senha`, `nome`, `endereco`, `uf`, `telefone`, `cpf`, `email`) VALUES
(1, 'ps', '123', 'Paulo', 'Cajazeiras', 'BA', '71988778877', '01201201223', 'p@email.com'),
(3, 'psa', '123', 'Paulo', 'Portão', 'CE', '71988998899', '02302302312', 'paulao@gmail.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `veiculo`
--

CREATE TABLE `veiculo` (
  `id` int(11) NOT NULL,
  `placa` varchar(7) NOT NULL,
  `fabricante` varchar(20) NOT NULL,
  `modelo` varchar(15) NOT NULL,
  `anoModelo` int(11) NOT NULL,
  `qtdPortas` int(11) NOT NULL,
  `acessorios` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `veiculo`
--

INSERT INTO `veiculo` (`id`, `placa`, `fabricante`, `modelo`, `anoModelo`, `qtdPortas`, `acessorios`) VALUES
(1, 'ABC1234', 'Fiat', 'Uno', 2014, 5, 'Vidro elétrico');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `aluguel`
--
ALTER TABLE `aluguel`
  ADD PRIMARY KEY (`idAluguel`),
  ADD KEY `idCliente` (`idCliente`),
  ADD KEY `idVeiculo` (`idVeiculo`);

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `cpf` (`cpf`);

--
-- Índices de tabela `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `placa` (`placa`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluguel`
--
ALTER TABLE `aluguel`
  MODIFY `idAluguel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `aluguel`
--
ALTER TABLE `aluguel`
  ADD CONSTRAINT `aluguel_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `aluguel_ibfk_2` FOREIGN KEY (`idVeiculo`) REFERENCES `veiculo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
