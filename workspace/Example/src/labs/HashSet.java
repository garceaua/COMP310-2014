package labs;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;


public class HashSet<AnyType> extends AbstractCollection<AnyType> implements Set<AnyType>
{
    
    public HashSet( )
    {
        allocateArray( DEFAULT_TABLE_SIZE );
        clear( );
    }
    
    
    public HashSet( Collection<? extends AnyType> other )
    {
        allocateArray( nextPrime( other.size( ) * 2 ) );
        clear( );
        
        for( AnyType val : other )
            add( val );    
    }
    
   
    public int size( )
    {
        return currentSize;
    }
    
    
    
    public AnyType getMatch( AnyType arg0 )
    {
        int currentPos = findPos( arg0 );

        if( isActive( array, currentPos ) )
            return (AnyType) array[ currentPos ].element;
        return null;
    }
    
   
    public boolean contains( Object arg0 )
    {
        return isActive( array, findPos( arg0 ) );
    }
    
   
    private static boolean isActive( HashEntry [ ] arr, int pos )
    {
        return arr[ pos ] != null && arr[ pos ].isActive;
    }
    
   
    public boolean add( AnyType arg0 )
    {
        int currentPos = findPos( arg0 );
        if( isActive( array, currentPos ) )
            return false;
       
        if( array[ currentPos ] == null ) 
            occupied++;
        array[ currentPos ] = new HashEntry( arg0, true );
        currentSize++;
        modCount++;
        
        if( occupied > array.length / 2 )        
            rehash( );
                
        return true;                   
    }
    
    
    private void rehash( )
    {
        HashEntry [ ] oldArray = array;
        
            // Create a new, empty table
        allocateArray( nextPrime( 4 * size( ) ) );
        currentSize = 0;
        occupied = 0;
        
            // Copy table over
        for( int i = 0; i < oldArray.length; i++ )
            if( isActive( oldArray, i ) ) 
                add( (AnyType) oldArray[ i ].element );
    }
    
   
    public boolean remove( Object arg0 )
    {
        int currentPos = findPos( arg0 );
        if( !isActive( array, currentPos ) )
            return false;
        
        array[ currentPos ].isActive = false;
        currentSize--;
        modCount++;    
        
        if( currentSize < array.length / 8 )
            rehash( );
    
        return true;
    }
   
    public void clear( )
    {
        currentSize = occupied = 0;
        modCount++;
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }
    
    
    public Iterator<AnyType> iterator( )
    {
        return new HashSetIterator( );
    }
    
   
    private class HashSetIterator implements Iterator<AnyType>
    {
        private int expectedModCount = modCount;
        private int currentPos = -1;
        private int visited = 0;       
        
        public boolean hasNext( )
        {
            if( expectedModCount != modCount )
                throw new ConcurrentModificationException( );
            
            return visited != size( );    
        }
        
        public AnyType next( )
        {
            if( !hasNext( ) )
                throw new NoSuchElementException( );
                          
            do
            {
                currentPos++;
            } while( currentPos < array.length && !isActive( array, currentPos ) );
                            
            visited++;
            return (AnyType) array[ currentPos ].element;    
        }
        
        public void remove( )
        {
            if( expectedModCount != modCount )
                throw new ConcurrentModificationException( );              
            if( currentPos == -1 || !isActive( array, currentPos ) )
                throw new IllegalStateException( );
    
            array[ currentPos ].isActive = false;
            currentSize--;
            visited--;
            modCount++;
            expectedModCount++;
        }
    }
    
    private static class HashEntry implements Serializable
    {
        public Object  element;   // the element
        public boolean isActive;  // false if marked deleted

        public HashEntry( Object e )
        {
            this( e, true );
        }

        public HashEntry( Object e, boolean i )
        {
            element  = e;
            isActive = i;
        }
    }
    
    
    
    private int findPos( Object arg0 )
    {
        int offset = 1;
        int currentPos = ( arg0 == null ) ? 0 : Math.abs( arg0.hashCode( ) % array.length );

        while( array[ currentPos ] != null )
        {
            if( arg0 == null )
            {
                if( array[ currentPos ].element == null )
                    break;
            }
            else if( arg0.equals( array[ currentPos ].element ) )   
                break; 
            
            currentPos += offset;                  // Compute ith probe
            offset += 2;
            if( currentPos >= array.length )       // Implement the mod
                currentPos -= array.length;
        }

        return currentPos;
    }
    
    
   
    private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

   
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }
    
    private static final int DEFAULT_TABLE_SIZE = 101;
    
    private int currentSize = 0;
    private int occupied = 0;
    private int modCount = 0;
    private HashEntry [ ] array;
}

