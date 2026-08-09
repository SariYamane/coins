// The name of a package is "coins.ssa"
package coins.ssa;

import coins.backend.LocalTransformer;
import coins.backend.Data;
import coins.backend.Function;
import coins.backend.cfg.BasicBlk;
import coins.backend.util.BiLink;
import coins.backend.lir.LirNode;
import coins.backend.util.ImList;
import coins.backend.cfg.FlowGraph;

// Implement LocalTransformer
public class PrintFlow implements LocalTransformer {

    // It is convenient to record the following environment and symbol table. 
    private SsaEnvironment env;
    private SsaSymTab sstab;

    public PrintFlow(SsaEnvironment e, SsaSymTab tab) {
	env = e;
	sstab = tab;
    }

    // Set a name and a brief explanation of your optimizer!
    public String name() { return "PrintFlow"; }
    public String subject() {
	return "Printing a control flow graph.";
    }

    // Use the other doIt method for your convenience!
    public boolean doIt(Data data, ImList args) { return true; }

    // The doIt is called by SSADriver. 
    // Parameters function and args are also provided by SSADriver.
    public boolean doIt(Function function,ImList args) {
	// making a control graph.
	FlowGraph flow = function.flowGraph();

	// BiLink is a bidirectional list structure which is often used in COINS.
	// Method "first" returns the first cell of the list.
	// Method "atEnd" returns true if a current cell is the next to the last cell; 
	// otherwise it returns false;
	// Method "next" is used if you would like to visit cells forwardly.
	// Method "prev" is used if you would like to visit cells backwardly.

	//basicBlkList returns a list of all the basic blocks.
	for(BiLink bbl = flow.basicBlkList.first(); !bbl.atEnd(); bbl=bbl.next()){
	    // Method "elem" returns a content of a cell.
	    // It has to be casted to a suitable type. In this case, 
	    // it is "BasicBlk" which represents the type of a basic block. 
	    BasicBlk bb=(BasicBlk)bbl.elem();

	    // Method "predList" returns a list of predecessors of bb.
	    for (BiLink pl  = bb.predList().first(); !pl.atEnd(); pl = pl.next()) {
		BasicBlk p = (BasicBlk) pl.elem();

		// Each BasicBlk object has its own id. 
		System.out.println("["+ p.id + "] ->");
	    }
	    // Method "predList" returns a list of successors of bb.
	    for (BiLink sl  = bb.succList().first(); !sl.atEnd(); sl = sl.next()) {
		BasicBlk s = (BasicBlk) sl.elem();
		System.out.println("-> ["+ s.id + "]");
	    }
	    System.out.println("["+bb.id+"]:");

	    // Method "instrList" returns a list of a node which consists of a tree structure
	    // which represents each statement. 
	    for(BiLink nodel=bb.instrList().first();!nodel.atEnd();nodel = nodel.next()){
		LirNode node = (LirNode)nodel.elem();
		System.out.println(node.toString());
	    }
	    System.out.print("\n");
	}

	// If you have modified the program, you have to touch it.
	//f.flowGraph().touch();

	// The last of "doIt" returns true.
	return(true);

    }
}
	