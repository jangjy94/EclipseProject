package page;

public class Pager {
	
	public static final int PAGE_SCALE = 10; // 페이지당 게시물 수(10개)
	public static final int BLOCK_SCALE = 10; // 페이지의 블럭 갯수
	private int curPage; // 현재 페이지
	private int prevPage; // 이전 페이지
	private int nextPage; // 다음 페이지
	private int totPage; // 전체 페이지 갯수
	private int totBlock; // 전체 페이지 블럭
	private int curBlock; // 현재 페이지 블럭
	private int prevBlock; // 이전 페이지 블럭
	private int nextBlock; // 다음 페이지 블럭
	private int pageBegin; // #{start}에 전달될 값
	private int pageEnd; // #{end}에 전달될 값
	private int blockStart; // 페이지 블럭에 시작 페이지 번호
	private int blockEnd; // 페이지 블럭에 끝 페이지 번호

	// Pager(레코드갯수, 보여줄페이지번호)
	public Pager(int count, int curPage) {
		curBlock = 1; // 페이지 블럭을 1로 초기화
		this.curPage = curPage;
		setTotPage(count); // 전체페이지 갯수 계산
		setPageRange(); // #{start}, #{end} 값 계산
		setTotBlock(); // 페이지 블럭의 갯수 계산
		setBlockRange(); // 페이지 블럭의 범의 설정
	}
	
	//페이지 블럭의 범의 설정
	public void setBlockRange() {
		// 현재페이지가 몇번째 페이지 블럭에 속하는지 계산
		// ((현재페이지 - 1) / 페이지블럭단위) + 1
		curBlock = (int)Math.ceil((curPage - 1) / BLOCK_SCALE +1);
		// (현재블럭 - 1) * 블록단위 + 1
		blockStart = (curBlock - 1) * BLOCK_SCALE + 1;
		blockEnd = blockStart + (BLOCK_SCALE -1);
		// 블럭의 마지막 페이지번호가 범위를 초과하지 않도록 처리
		if(blockEnd > totPage) {
			blockEnd = totPage;
		}
		/*[이전] 11 12 13 14 15 16 17 18 19 20 [다음]
			[이전] 21 22 23 24 25 26 27 28 29 30 [다음]
			현재 블럭이 1이면
			1 2 3 4 5 6 7 8 9 10 [다음] */
		prevPage = curBlock == 1 ? 1 : (curBlock - 1) * BLOCK_SCALE;
		// [다음]을 눌렀을 때 이동할 페이지
		nextPage = curBlock > totBlock ? (curBlock * BLOCK_SCALE) : (curBlock * BLOCK_SCALE) + 1;
	}

	public void setPageRange() {
		// 시작번호 = (현재페이지 - 1) * 페이지당 게시물수 + 1
		pageBegin = (curPage - 1) * PAGE_SCALE + 1;
		// 끝번호 = 시작번호 + (페이지당 게시물수 - 1
		pageEnd = pageBegin + (PAGE_SCALE -1);
	}

	// getter, setter만 생성
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotPage() {
		return totPage;
	}
	// 전체 페이지 갯수 계산
	public void setTotPage(int count) {
		// 991 / 10 => 99.1 올림 => 100
		// ceil()올림, round()반올림, floor()버림
		totPage = (int)Math.ceil(count * 1.0 / PAGE_SCALE);
	}
	public int getTotBlock() {
		return totBlock;
	}
	// 페이지블럭의 갯수 계산
	public void setTotBlock() {
		// 991 : 991/10 = > 99, 한 페이지 누락이기 때문에 올림처리
		totBlock = (int)Math.ceil(totPage * 1.0 / BLOCK_SCALE);
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getPrevBlock() {
		return prevBlock;
	}
	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}
	public int getNextBlock() {
		return nextBlock;
	}
	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}
	public int getPageBegin() {
		return pageBegin;
	}
	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getBlockStart() {
		return blockStart;
	}
	public void setBlockStart(int blockStart) {
		this.blockStart = blockStart;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}		
}