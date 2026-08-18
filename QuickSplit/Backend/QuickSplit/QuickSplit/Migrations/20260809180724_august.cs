using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace QuickSplit.Migrations
{
    /// <inheritdoc />
    public partial class august : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "TransactionUser");

            migrationBuilder.DropColumn(
                name: "SharesAsJson",
                table: "Transaction");

            migrationBuilder.AddColumn<int>(
                name: "Userid",
                table: "Transaction",
                type: "int",
                nullable: true);

            migrationBuilder.CreateTable(
                name: "Share",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    UserID = table.Column<int>(type: "int", nullable: false),
                    TransactionId = table.Column<int>(type: "int", nullable: false),
                    Amount = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Share", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Share_Transaction_TransactionId",
                        column: x => x.TransactionId,
                        principalTable: "Transaction",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Transaction_Userid",
                table: "Transaction",
                column: "Userid");

            migrationBuilder.CreateIndex(
                name: "IX_Share_TransactionId",
                table: "Share",
                column: "TransactionId");

            migrationBuilder.AddForeignKey(
                name: "FK_Transaction_User_Userid",
                table: "Transaction",
                column: "Userid",
                principalTable: "User",
                principalColumn: "id");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Transaction_User_Userid",
                table: "Transaction");

            migrationBuilder.DropTable(
                name: "Share");

            migrationBuilder.DropIndex(
                name: "IX_Transaction_Userid",
                table: "Transaction");

            migrationBuilder.DropColumn(
                name: "Userid",
                table: "Transaction");

            migrationBuilder.AddColumn<string>(
                name: "SharesAsJson",
                table: "Transaction",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");

            migrationBuilder.CreateTable(
                name: "TransactionUser",
                columns: table => new
                {
                    TransactionsId = table.Column<int>(type: "int", nullable: false),
                    Userid = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TransactionUser", x => new { x.TransactionsId, x.Userid });
                    table.ForeignKey(
                        name: "FK_TransactionUser_Transaction_TransactionsId",
                        column: x => x.TransactionsId,
                        principalTable: "Transaction",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_TransactionUser_User_Userid",
                        column: x => x.Userid,
                        principalTable: "User",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_TransactionUser_Userid",
                table: "TransactionUser",
                column: "Userid");
        }
    }
}
