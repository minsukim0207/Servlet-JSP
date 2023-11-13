package paginationDAO;

public class PaginationUtil {
	// 1. ������ ������ 2. ������ ������ 3. ��Ż������
	
	/*
	 * �־��� ������ ��ȣ�� ������ ũ�⸦ �̿��ؼ� ��ȸ�� ������ �� ������ �ε����� ���
	 * ������ ��ȣ�� 1���� �����ϰ� �� ������ �� �� ���� �������� ������ ���� ���� ���
	 */
	public static int paginationStart(int pageNumber, int pageSize) {
		return (pageNumber) - 1 * pageSize + 1;
	}
	
	/*
	 * �־��� ������ ��ȣ�� ������ ũ�⸦ �̿��ؼ� ��ȸ�� ���� �� ������ �ε����� ���
	 * ������ ��ȣ�� 1���� �����ϰ� �� ������ �� �� ���� �������� ������ ���� ���� ���
	 */
	public static int paginationEnd(int pageNumber, int pageSize) {
		return pageNumber * pageSize;
	}
	
	/*
	 * ��ü ������ ���� ������ ũ�⸦ �̿��ؼ� ��ü ������ ���� ���
	 * ��ü ����� ������ ũ��� �������� �Ҽ��� ������ ���� ���� ��쿡�� �ø� �����Ͽ� ��� ó��
	 */
	public static int paginationTotalPages(int totalItems, int pageSize) {
		return (int) Math.ceil((double)totalItems / pageSize);
	}
}
