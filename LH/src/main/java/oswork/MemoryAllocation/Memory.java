package oswork.MemoryAllocation;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 基于顺序搜索的动态分区分配算法
 */
public class Memory {
    private int size;           //内存大小
    private int pointer;        //上次分配空闲区位置
    private LinkedList<Block> blockList;   //内存块链
    private int minBlockSize = 5;       //最小块大小，分配过程中如果切割块剩余的容量小于此值直接全部分配防止生成过多内存碎片
    //默认构造函数内存大小100
    public Memory() {
        this.size = 100;
        this.pointer = 0;
        this.blockList = new LinkedList();
        blockList.add(new Block(0,size));

    }
    public Memory(int size) {
        this.size = size;
        this.pointer = 0;
        this.blockList = new LinkedList<>();
        blockList.add(new Block(0,size));
    }


    /**
     * 内存按需分配
     * @param size
     */
    public void allocate(int size,int algorithm) {


        switch (algorithm){
            case 1: {
                boolean result = firstFit(size);
                checkAllocated(result);
                break;
            }

            case 2: {
                boolean result= nextFit(size);
                checkAllocated(result);
                break;
            }
            case 3: {
                boolean result = bestFit(size);
                checkAllocated(result);
                break;

            }
            case 4: {
                boolean result = worstFit(size);
                checkAllocated(result);
                break;
            }
            default:
                System.out.println("请重新选择!");
        }





    }

    /**
     * 执行分配
     * @param location
     * @param size
     * @param tmp
     */
    private void doAllocate(int location,int size,Block tmp) {
        if((tmp.size - size) < minBlockSize) {
            //防止产生过多内存碎片  直接分配
            tmp.isFree = false;
        }else {
            //内存块切分
            blockList.add(location + 1,new Block(tmp.head + size,tmp.size - size));
            tmp.size = size;
            tmp.isFree = false;
        }
        System.out.println("已成功分配内存:" + size);

    }

    /**
     * 首次适应分配算法
     */
    private boolean firstFit(int size) {
        for (pointer = 0; pointer < blockList.size(); pointer++) {
            Block block = blockList.get(pointer);
            if(block.isFree && block.size >= size) {
                doAllocate(pointer,size,block);
                return true;
            }
        }
        //没有找到合适的块进行分配
        return false;
    }

    /**
     * 循环首次适应分配算法
     */
    private boolean nextFit(int size) {
        /**
         * 从上次分配空闲区位置开始分配
         */
        Block block = blockList.get(pointer);
        if(block.isFree && block.size > size) {
            doAllocate(pointer,size,block);
            pointer = ++pointer;
            return true;
        }
        int i = (pointer + 1)%blockList.size();
        while (i != pointer) {
            Block tmp = blockList.get(i);
            if(tmp.isFree && tmp.size > size) {
                doAllocate(i,size,tmp);
                pointer = ++i;
                return true;
            }
            i = (i + 1)%blockList.size();
        }
        return false;
    }

    /**
     * 最佳适应分配算法
     */
    private boolean bestFit(int size) {
        /**
         * 找出分配后剩余空间最小的block
         */
        int min = this.size;
        int index = -1;
        for (int pointer = 0; pointer < blockList.size(); pointer++) {
            Block block = blockList.get(pointer);
            if(block.isFree && block.size > size) {
                if(block.size - size < min) {
                    min = block.size - size;
                    index = pointer;
                }
            }

        }
        if(index != -1) {
            doAllocate(index,size,blockList.get(index));
            return true;
        }
        return false;
    }

    /**
     * 最坏适应分配算法
     */
    private boolean worstFit(int size) {
        /**
         * 找出分配后剩余空间最大的block
         */
        int max = 0;
        int index = -1;
        for (int pointer = 0; pointer < blockList.size(); pointer++) {
            Block block = blockList.get(pointer);
            if(block.isFree && block.size > size) {
                if(block.size - size > max) {
                    max = block.size - size;
                    index = pointer;
                }
            }

        }
        if(index != -1) {
            doAllocate(index,size,blockList.get(index));
            return true;
        }
        return false;
    }
    private void checkAllocated(boolean result) {
        if(!result) {
            System.out.println("分配失败！");
        }
    }


    /**
     * 内存回收
     * @param id 指定要回收的分区好号
     */
    public void collection(int id){
        if (id >= blockList.size()){
            System.out.println("无此分区编号!");
            return;
        }
        Block tmp = blockList.get(id);
        int size = tmp.size;
        if (tmp.isFree) {
            System.out.println("指定分区未被分配, 无需回收");
            return;
        }
        //如果回收分区不是尾分区且后一个分区为空闲, 则与后一个分区合并
        if (id < blockList.size() - 1 && blockList.get(id + 1).isFree){
            Block next = blockList.get(id + 1);
            tmp.size += next.size;
            blockList.remove(next);
        }
        //如果回收分区不是首分区且前一个分区为空闲, 则与前一个分区合并
        if (id > 0 && blockList.get(id - 1).isFree){
            Block previous = blockList.get(id - 1);
            previous.size += tmp.size;
            blockList.remove(id);
            id--;
        }
        blockList.get(id).isFree = true;
        System.out.println("内存回收成功!, 本次回收了 " + size + "KB 空间!");
    }


    /**
     * 显示内存分区情况
     */
    private void showBlockList() {
        System.out.println("------------------------------------");
        System.out.println("分区编号\t分区始址\t分区大小\t空闲状态\t");
        System.out.println("------------------------------------");
        for (int i = 0; i < blockList.size(); i++){
            Block tmp = blockList.get(i);
            System.out.println(i + "\t\t" + tmp.head + "\t\t" +
                    tmp.size + "  \t" + tmp.isFree);
        }
        System.out.println("------------------------------------");
    }

    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入服务器内存大小：");
        int memorySize = in.nextInt();
        Memory memory = new Memory(memorySize);
        /**
         * 根据用户选择不同的内存分配算法进行内存分配
         */
        System.out.println("1.FirstFit 2.NextFit 3.BestFit 4.WorstFit");
        System.out.print("请选择分配算法:");
        int algorithm = in.nextInt();
        System.out.println("请输入需要分配内存的大小：（输入-1停止内存分配）");
        while (true) {
            int allocateSize = in.nextInt();
            if(allocateSize == -1) break;
            memory.allocate(allocateSize,algorithm);
            memory.showBlockList();
        }

    }

    class Block {
        private int size;       //内存块大小
        private int head;       //内存块起始地址
        private boolean isFree;     //内存块是否空闲

        public Block(int head,int size) {
            this.size = size;
            this.head = head;
            this.isFree = true;
        }

    }


}