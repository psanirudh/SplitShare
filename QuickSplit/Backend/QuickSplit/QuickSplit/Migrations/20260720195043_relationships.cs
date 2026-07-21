using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace QuickSplit.Migrations
{
    /// <inheritdoc />
    public partial class relationships : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
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
                name: "IX_Transaction_groupId",
                table: "Transaction",
                column: "groupId");

            migrationBuilder.CreateIndex(
                name: "IX_TransactionUser_Userid",
                table: "TransactionUser",
                column: "Userid");

            migrationBuilder.AddForeignKey(
                name: "FK_Transaction_groups_groupId",
                table: "Transaction",
                column: "groupId",
                principalTable: "groups",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Transaction_groups_groupId",
                table: "Transaction");

            migrationBuilder.DropTable(
                name: "TransactionUser");

            migrationBuilder.DropIndex(
                name: "IX_Transaction_groupId",
                table: "Transaction");
        }
    }
}
