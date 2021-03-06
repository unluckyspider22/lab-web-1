USE [master]
GO
/****** Object:  Database [ResourceSharing]    Script Date: 8/9/2021 11:43:04 AM ******/
CREATE DATABASE [ResourceSharing]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ResourceSharing', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ResourceSharing.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ResourceSharing_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ResourceSharing_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [ResourceSharing] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ResourceSharing].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ResourceSharing] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ResourceSharing] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ResourceSharing] SET ARITHABORT OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ResourceSharing] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ResourceSharing] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ResourceSharing] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ResourceSharing] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ResourceSharing] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ResourceSharing] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ResourceSharing] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ResourceSharing] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ResourceSharing] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ResourceSharing] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ResourceSharing] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ResourceSharing] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ResourceSharing] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ResourceSharing] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ResourceSharing] SET RECOVERY FULL 
GO
ALTER DATABASE [ResourceSharing] SET  MULTI_USER 
GO
ALTER DATABASE [ResourceSharing] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ResourceSharing] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ResourceSharing] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ResourceSharing] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ResourceSharing] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ResourceSharing] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'ResourceSharing', N'ON'
GO
ALTER DATABASE [ResourceSharing] SET QUERY_STORE = OFF
GO
USE [ResourceSharing]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 8/9/2021 11:43:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[Email] [varchar](50) NOT NULL,
	[Password] [varchar](50) NULL,
	[Name] [varchar](50) NULL,
	[RoleId] [int] NOT NULL,
	[VerifyCode] [varchar](50) NULL,
	[AccountStatusId] [int] NOT NULL,
	[InsDate] [datetime] NULL,
	[IsDeleted] [bit] NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AccountStatus]    Script Date: 8/9/2021 11:43:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountStatus](
	[AccountStatusId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nchar](20) NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[AccountStatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BookingDetails]    Script Date: 8/9/2021 11:43:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookingDetails](
	[BookingDetailId] [int] IDENTITY(1,1) NOT NULL,
	[BookingId] [int] NULL,
	[ResourceId] [int] NULL,
	[Quantity] [int] NULL,
	[IsDeleted] [bit] NULL,
 CONSTRAINT [PK_BookingDetails] PRIMARY KEY CLUSTERED 
(
	[BookingDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bookings]    Script Date: 8/9/2021 11:43:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bookings](
	[BookingId] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](50) NULL,
	[BookingDate] [datetime] NULL,
	[RequestMessage] [nvarchar](500) NULL,
	[ResponseMessage] [nvarchar](500) NULL,
	[CensorName] [nchar](50) NULL,
	[BookingStatusId] [int] NULL,
	[ReturnDate] [datetime] NULL,
	[InsDate] [datetime] NULL,
	[ResourceId] [int] NULL,
	[Quantity] [int] NULL,
	[IsDeleted] [bit] NULL,
 CONSTRAINT [PK_Bookings] PRIMARY KEY CLUSTERED 
(
	[BookingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BookingStatus]    Script Date: 8/9/2021 11:43:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookingStatus](
	[BookingStatusId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
 CONSTRAINT [PK_BookingStatus] PRIMARY KEY CLUSTERED 
(
	[BookingStatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 8/9/2021 11:43:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[CategoryId] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](100) NULL,
	[IsForManager] [bit] NULL,
	[IsForLeader] [bit] NULL,
	[IsForEmployee] [bit] NULL,
	[IsDeleted] [bit] NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Resources]    Script Date: 8/9/2021 11:43:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Resources](
	[ResourceId] [int] IDENTITY(1,1) NOT NULL,
	[ResourceName] [nvarchar](100) NULL,
	[CategoryId] [int] NULL,
	[Color] [nchar](20) NULL,
	[Quantity] [int] NULL,
	[AvailableQuantity] [int] NULL,
	[IsAvailable] [bit] NULL,
	[IsDeleted] [bit] NULL,
 CONSTRAINT [PK_Resources] PRIMARY KEY CLUSTERED 
(
	[ResourceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 8/9/2021 11:43:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[RoleId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](20) NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Accounts] ([Email], [Password], [Name], [RoleId], [VerifyCode], [AccountStatusId], [InsDate], [IsDeleted]) VALUES (N'donguyen.higashi@gmail.com', N'123', N'Do Nguyen', 3, N'sRNPLcIF', 2, CAST(N'2021-05-20T00:00:00.000' AS DateTime), 0)
INSERT [dbo].[Accounts] ([Email], [Password], [Name], [RoleId], [VerifyCode], [AccountStatusId], [InsDate], [IsDeleted]) VALUES (N'nguyenga22799@gmail.com', N'123', N'Manager', 1, N'asH7fCgT', 2, CAST(N'2021-05-20T00:00:00.000' AS DateTime), 0)
INSERT [dbo].[Accounts] ([Email], [Password], [Name], [RoleId], [VerifyCode], [AccountStatusId], [InsDate], [IsDeleted]) VALUES (N'wowo@gmail.com', N'123', N'Tui Lead', 2, N'dg6Gyhy7', 2, CAST(N'2021-05-20T00:00:00.000' AS DateTime), 0)
GO
SET IDENTITY_INSERT [dbo].[AccountStatus] ON 

INSERT [dbo].[AccountStatus] ([AccountStatusId], [Name]) VALUES (1, N'New                 ')
INSERT [dbo].[AccountStatus] ([AccountStatusId], [Name]) VALUES (2, N'Active              ')
INSERT [dbo].[AccountStatus] ([AccountStatusId], [Name]) VALUES (3, N'Disable             ')
SET IDENTITY_INSERT [dbo].[AccountStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[Bookings] ON 

INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1008, N'donguyen.higashi@gmail.com', CAST(N'2021-05-28T16:58:00.000' AS DateTime), N'd', N'aaa', N'tui                                               ', 2, CAST(N'2021-05-28T16:58:00.000' AS DateTime), CAST(N'2021-05-28T16:02:54.850' AS DateTime), 7, 3, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1009, N'donguyen.higashi@gmail.com', CAST(N'2021-06-09T23:48:00.000' AS DateTime), N'asd', NULL, N'Manager                                           ', 1, CAST(N'2021-06-09T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:52:30.053' AS DateTime), 13, 5, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1010, N'donguyen.higashi@gmail.com', CAST(N'2021-06-17T23:48:00.000' AS DateTime), N'asd', NULL, N'Manager                                           ', 3, CAST(N'2021-06-24T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:53:08.147' AS DateTime), 13, 10, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1011, N'donguyen.higashi@gmail.com', CAST(N'2021-06-09T23:48:00.000' AS DateTime), N'2222', NULL, NULL, 1, CAST(N'2021-06-15T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:53:31.953' AS DateTime), 13, 10, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1012, N'donguyen.higashi@gmail.com', CAST(N'2021-06-09T23:48:00.000' AS DateTime), N'2222', NULL, NULL, 1, CAST(N'2021-06-15T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:53:37.157' AS DateTime), 13, 10, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1013, N'donguyen.higashi@gmail.com', CAST(N'2021-06-03T23:48:00.000' AS DateTime), N'jkl', NULL, NULL, 1, CAST(N'2021-06-07T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:54:04.370' AS DateTime), 11, 20, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1014, N'donguyen.higashi@gmail.com', CAST(N'2021-06-03T23:48:00.000' AS DateTime), N'jkl', NULL, NULL, 1, CAST(N'2021-06-07T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:54:10.737' AS DateTime), 11, 20, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1015, N'donguyen.higashi@gmail.com', CAST(N'2021-06-04T23:48:00.000' AS DateTime), N'tv', NULL, NULL, 1, CAST(N'2021-06-09T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:54:28.923' AS DateTime), 12, 10, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1016, N'donguyen.higashi@gmail.com', CAST(N'2021-06-04T23:48:00.000' AS DateTime), N'tv', NULL, NULL, 1, CAST(N'2021-06-09T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:54:31.123' AS DateTime), 12, 10, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1017, N'donguyen.higashi@gmail.com', CAST(N'2021-06-12T23:48:00.000' AS DateTime), N'5123', NULL, NULL, 1, CAST(N'2021-06-25T23:49:00.000' AS DateTime), CAST(N'2021-06-01T23:55:05.333' AS DateTime), 26, 10, 1)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1018, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:13.280' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1019, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:14.237' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1020, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:15.137' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1021, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:15.560' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1022, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:15.903' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1023, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:16.150' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1024, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:16.440' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1025, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:16.753' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1026, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:17.070' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1027, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:17.343' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1028, N'donguyen.higashi@gmail.com', CAST(N'2021-06-10T14:54:00.000' AS DateTime), N'hhh', NULL, NULL, 1, CAST(N'2021-06-18T14:54:00.000' AS DateTime), CAST(N'2021-06-02T14:54:17.613' AS DateTime), 27, 12, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1029, N'wowo@gmail.com', CAST(N'2021-06-08T23:30:00.000' AS DateTime), N'ddd', NULL, NULL, 1, CAST(N'2021-06-10T23:30:00.000' AS DateTime), CAST(N'2021-06-04T23:30:24.560' AS DateTime), 18, 3, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1030, N'wowo@gmail.com', CAST(N'2021-06-16T23:32:00.000' AS DateTime), N'gg', NULL, NULL, 1, CAST(N'2021-06-19T23:32:00.000' AS DateTime), CAST(N'2021-06-04T23:32:10.507' AS DateTime), 7, 4, 0)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1031, N'wowo@gmail.com', CAST(N'2021-06-18T23:34:00.000' AS DateTime), N'haha', NULL, NULL, 1, CAST(N'2021-06-23T23:34:00.000' AS DateTime), CAST(N'2021-06-04T23:34:23.233' AS DateTime), 19, 2, 1)
INSERT [dbo].[Bookings] ([BookingId], [Email], [BookingDate], [RequestMessage], [ResponseMessage], [CensorName], [BookingStatusId], [ReturnDate], [InsDate], [ResourceId], [Quantity], [IsDeleted]) VALUES (1032, N'wowo@gmail.com', CAST(N'2021-06-18T23:34:00.000' AS DateTime), N'haha', NULL, NULL, 1, CAST(N'2021-06-23T23:34:00.000' AS DateTime), CAST(N'2021-06-04T23:34:27.710' AS DateTime), 19, 2, 1)
SET IDENTITY_INSERT [dbo].[Bookings] OFF
GO
SET IDENTITY_INSERT [dbo].[BookingStatus] ON 

INSERT [dbo].[BookingStatus] ([BookingStatusId], [Name]) VALUES (1, N'New')
INSERT [dbo].[BookingStatus] ([BookingStatusId], [Name]) VALUES (2, N'Approved')
INSERT [dbo].[BookingStatus] ([BookingStatusId], [Name]) VALUES (3, N'Rejected')
SET IDENTITY_INSERT [dbo].[BookingStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([CategoryId], [CategoryName], [IsForManager], [IsForLeader], [IsForEmployee], [IsDeleted]) VALUES (1, N'Furniture', 1, 1, 1, 0)
INSERT [dbo].[Categories] ([CategoryId], [CategoryName], [IsForManager], [IsForLeader], [IsForEmployee], [IsDeleted]) VALUES (2, N'Electronic Device', 1, 1, 1, 0)
INSERT [dbo].[Categories] ([CategoryId], [CategoryName], [IsForManager], [IsForLeader], [IsForEmployee], [IsDeleted]) VALUES (3, N'Car', 1, 1, 0, 0)
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Resources] ON 

INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (1, N'Table', 1, N'Grown               ', 100, 100, NULL, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (2, N'Chair', 1, N'Red                 ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (3, N'Lamb', 1, N'Red                 ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (4, N'Book', 1, N'Green               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (5, N'Cabinet', 1, N'White               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (6, N'Brick', 1, N'White               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (7, N'Bed', 1, N'Blue                ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (8, N'Blanker', 1, N'Grey                ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (9, N'Fan', 2, N'Grey                ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (10, N'Fridge', 2, N'Purple              ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (11, N'Computer', 2, N'White               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (12, N'TV', 2, N'Black               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (13, N'Air Conditioner', 2, N'White               ', 100, 90, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (14, N'Smart Phone', 2, N'Redd                ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (15, N'Laptop', 2, N'Blue                ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (16, N'Light', 2, N'Pink                ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (17, N'Lamborghini', 3, N'Red                 ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (18, N'Audi', 3, N'Blue                ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (19, N'BMW', 3, N'Yellow              ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (20, N'Mecedes', 3, N'Black               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (21, N'Honda', 3, N'White               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (22, N'Toyota', 3, N'White               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (23, N'Suzuki', 3, N'White               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (24, N'Ferrari', 3, N'Red                 ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (25, N'Mouse', 2, N'Black               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (26, N'Keyboard', 2, N'Black               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (27, N'Module Wifi', 2, N'White               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (28, N'Computer Screen', 2, N'Black               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (29, N'Speaker', 2, N'Blue                ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (30, N'Headphone', 2, N'Black               ', 100, 100, 1, 0)
INSERT [dbo].[Resources] ([ResourceId], [ResourceName], [CategoryId], [Color], [Quantity], [AvailableQuantity], [IsAvailable], [IsDeleted]) VALUES (31, N'Smart Watch', 2, N'Black               ', 100, 100, 1, 0)
SET IDENTITY_INSERT [dbo].[Resources] OFF
GO
SET IDENTITY_INSERT [dbo].[Roles] ON 

INSERT [dbo].[Roles] ([RoleId], [Name]) VALUES (1, N'Manager')
INSERT [dbo].[Roles] ([RoleId], [Name]) VALUES (2, N'Leader')
INSERT [dbo].[Roles] ([RoleId], [Name]) VALUES (3, N'Employee')
SET IDENTITY_INSERT [dbo].[Roles] OFF
GO
ALTER TABLE [dbo].[Accounts] ADD  CONSTRAINT [DF_Accounts_IsDeleted]  DEFAULT ((0)) FOR [IsDeleted]
GO
ALTER TABLE [dbo].[Bookings] ADD  CONSTRAINT [DF_Bookings_IsDeleted]  DEFAULT ((0)) FOR [IsDeleted]
GO
ALTER TABLE [dbo].[Categories] ADD  CONSTRAINT [DF_Categories_IsDeleted]  DEFAULT ((0)) FOR [IsDeleted]
GO
ALTER TABLE [dbo].[Resources] ADD  CONSTRAINT [DF_Resources_IsAvailable]  DEFAULT ((1)) FOR [IsAvailable]
GO
ALTER TABLE [dbo].[Resources] ADD  CONSTRAINT [DF_Resources_IsDeleted]  DEFAULT ((0)) FOR [IsDeleted]
GO
ALTER TABLE [dbo].[Accounts]  WITH CHECK ADD  CONSTRAINT [FK_Accounts_AccountStatus] FOREIGN KEY([AccountStatusId])
REFERENCES [dbo].[AccountStatus] ([AccountStatusId])
GO
ALTER TABLE [dbo].[Accounts] CHECK CONSTRAINT [FK_Accounts_AccountStatus]
GO
ALTER TABLE [dbo].[Accounts]  WITH CHECK ADD  CONSTRAINT [FK_Accounts_Roles] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([RoleId])
GO
ALTER TABLE [dbo].[Accounts] CHECK CONSTRAINT [FK_Accounts_Roles]
GO
ALTER TABLE [dbo].[Bookings]  WITH CHECK ADD  CONSTRAINT [FK_Bookings_Accounts] FOREIGN KEY([Email])
REFERENCES [dbo].[Accounts] ([Email])
GO
ALTER TABLE [dbo].[Bookings] CHECK CONSTRAINT [FK_Bookings_Accounts]
GO
ALTER TABLE [dbo].[Bookings]  WITH CHECK ADD  CONSTRAINT [FK_Bookings_BookingStatus] FOREIGN KEY([BookingStatusId])
REFERENCES [dbo].[BookingStatus] ([BookingStatusId])
GO
ALTER TABLE [dbo].[Bookings] CHECK CONSTRAINT [FK_Bookings_BookingStatus]
GO
ALTER TABLE [dbo].[Bookings]  WITH CHECK ADD  CONSTRAINT [FK_Bookings_Resources] FOREIGN KEY([ResourceId])
REFERENCES [dbo].[Resources] ([ResourceId])
GO
ALTER TABLE [dbo].[Bookings] CHECK CONSTRAINT [FK_Bookings_Resources]
GO
ALTER TABLE [dbo].[Resources]  WITH CHECK ADD  CONSTRAINT [FK_Resources_Categories] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Categories] ([CategoryId])
GO
ALTER TABLE [dbo].[Resources] CHECK CONSTRAINT [FK_Resources_Categories]
GO
USE [master]
GO
ALTER DATABASE [ResourceSharing] SET  READ_WRITE 
GO
